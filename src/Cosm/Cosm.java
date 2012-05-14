package Cosm;

import java.net.URI;
import java.net.URL;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import Cosm.Client.CosmClient;

public class Cosm {
	public static final String VERSION = "1.0.0";
	
	private CosmClient client;

	public Cosm(String apikey) {
		client = new CosmClient(apikey);
	}

	public Cosm(String username,String password) {
		client = new CosmClient(username,password);
	}

	// get feed
	public Feed getFeed(int feedid) throws CosmException {
		try {			
			HttpGet hr = new HttpGet("http://api.cosm.com/v2/feeds/"+feedid+".json");
			HttpResponse response = client.execute(hr);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() == 200) {
				return CosmFactory.toFeed(client.getBody(response));				
			} else {
				throw new CosmException(response.getStatusLine().toString());				
			}			
		} catch ( Exception e) {		
			e.printStackTrace();
			throw new CosmException(e.getMessage());
		}
	}

	// get feeds with more search options
	// TODO: include lat, lon, distance, and distance_units in query
	public Feed[] getFeeds(String query,Content content,String tag,String user,String units,Status status,Order order,Boolean show_user) throws CosmException {
		String q = "";
		Boolean bAdd = false;
		
		if ( query != null ) {		
			if ( bAdd ) q += '&';
			q += "q=" + query;
			bAdd = true;
		}
		if ( content != null ) {
			if ( bAdd ) q += '&';
			q += "content=" + content.toString();
			bAdd = true;
		}		
		if ( tag != null ) {
			if ( bAdd ) q += '&';
			q += "tag=" + tag;
			bAdd = true;			
		}
		if ( user != null ) {
			if ( bAdd ) q += '&';
			q += "user=" + user;
			bAdd = true;
		}
		if ( units != null ) {
			if ( bAdd ) q += '&';
			q += "units=" + units;
			bAdd = true;
		}
		if ( status != null ) {
			if ( bAdd ) q += '&';
			q += "status=" + status.toString();
			bAdd = true;
		}		
		if ( order != null ) {
			if ( bAdd ) q += '&';
			q += "order=" + order.toString();
			bAdd = true;
		}		
		if ( show_user != null ) {
			if ( bAdd ) q += '&';
			q += "show_user=" + show_user.toString();
			bAdd = true;			
		}
		
		
		
		try {
			URI uri = new URI("http","api.cosm.com","/v2/feeds.json",q,null);
			
			//System.err.println(uri.toASCIIString());
			
			HttpGet hr = new HttpGet(uri);
			HttpResponse response = client.execute(hr);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() == 200) {
				return CosmFactory.toFeeds(client.getBody(response));				
			} else {
				throw new CosmException(response.getStatusLine().toString());				
			}			
		} catch ( Exception e) {		
			e.printStackTrace();
			throw new CosmException(e.getMessage());
		}		
	}
	
	// get feeds
	public Feed[] getFeeds() throws CosmException {
		//TODO: scrolling is not supported.
		return getFeeds(null,null,null,null,null,null,null,null);
	}

	// delete feed
	public void deleteFeed(Integer feedid) throws CosmException {
		try {
			HttpDelete hr = new HttpDelete("http://api.cosm.com/v2/feeds/"+feedid);
			HttpResponse response = client.execute(hr);
			StatusLine statusLine = response.getStatusLine();
			client.getBody(response);
			if ( statusLine.getStatusCode() != 200) {
				throw new CosmException(response.getStatusLine().toString());				
			}			
		} catch ( Exception e ) {		
			e.printStackTrace();
			throw new CosmException(e.getMessage());
		}		
	}

	// create feed
	public Feed createFeed(Feed feed) throws CosmException {
		try {
			HttpPost hr = new HttpPost("http://api.cosm.com/v2/feeds.json");
			hr.setEntity(new StringEntity(feed.toJSONObject().toString()));
			HttpResponse response = client.execute(hr);			
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() == 201 ) {
				String a[] = response.getHeaders("Location")[0].getValue().split("/");
				Integer feedid = Integer.parseInt(a[a.length -1]);
				client.getBody(response);
				return this.getFeed(feedid);
			} else {
				throw new CosmException(response.getStatusLine().toString());								
			}						
		} catch ( Exception e) {
			e.printStackTrace();
			throw new CosmException("Caught exception in create Feed" + e.getMessage());
		}
	}

	// update feed
	public void updateFeed(Feed feed) throws CosmException {
		try {
			HttpPut hr = new HttpPut("http://api.cosm.com/v2/feeds/" + feed.getId() + ".json");
			hr.setEntity(new StringEntity(feed.toJSONObject().toString()));
			HttpResponse response = client.execute(hr);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() != 200 ) {
				throw new CosmException(response.getStatusLine().toString());												
			}
		} catch ( Exception e) {
			e.printStackTrace();
			throw new CosmException("Caught exception in create Feed");
		}
	}

	// create group
	public Group createGroup(Group group) throws CosmException {
		try {
			HttpPost hr = new HttpPost("http://api.cosm.com/v2/groups.json");
			hr.setEntity(new StringEntity(group.toJSONObject().toString()));
			HttpResponse response = client.execute(hr);			
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() == 201 ) {
				return CosmFactory.toGroup(client.getBody(response));
			} else {
				throw new CosmException(response.getStatusLine().toString());								
			}						
		} catch ( Exception e) {
			e.printStackTrace();
			throw new CosmException("Caught exception in create Group" + e.getMessage());
		}
		
	}
	
	// get group
	public Group getGroup(String groupid) throws CosmException {
		try {
			HttpGet hr = new HttpGet("http://api.cosm.com/v2/groups/"+groupid+".json");
			HttpResponse response = client.execute(hr);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() == 200 ) {
				return CosmFactory.toGroup(client.getBody(response));
			} else {
				throw new CosmException(response.getStatusLine().toString());												
			}
		} catch ( Exception e) {
			e.printStackTrace();
			throw new CosmException("Caught exception in getGroup" + e.getMessage());
		}
	}
		
	// get groups
	public Group[] getGroups() throws CosmException {
		try {
			HttpGet hr = new HttpGet("http://api.cosm.com/v2/groups.json");
			HttpResponse response = client.execute(hr);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() == 200 ) {
				return CosmFactory.toGroups(client.getBody(response));
			} else {
				throw new CosmException(response.getStatusLine().toString());												
			}
		} catch ( Exception e ) {
			throw new CosmException("Caught exception in getGroups");
		}
	}
	
	// update group
	public void updateGroup(Group group) throws CosmException {
		try {
			HttpPut hr = new HttpPut("http://api.cosm.com/v2/groups/" + group.getGroupid() + ".json");
			hr.setEntity(new StringEntity(group.toJSONObject().toString()));
			HttpResponse response = client.execute(hr);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() != 200 ) {
				throw new CosmException(response.getStatusLine().toString());																
			}
		} catch ( Exception e) {
			e.printStackTrace();
			throw new CosmException("Caught exception in update group");
		}
	}
	
	// delete group
	public void deleteGroup(String groupid) throws CosmException {
		try {
			HttpDelete hr = new HttpDelete("http://api.cosm.com/v2/groups/"+groupid);			
			HttpResponse response = client.execute(hr);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() != 200 ) {
				throw new CosmException(response.getStatusLine().toString());																				
			}
		} catch ( Exception e) {		
			e.printStackTrace();
			throw new CosmException(e.getMessage());
		}		
	}
	
	// get datastream
	public Datastream getDatastream(Integer feedid, String datastreamid) throws CosmException {
		try {
			HttpGet request = new HttpGet("http://api.cosm.com/v2/feeds/"+feedid+"/datastreams/"+datastreamid+".json");
			HttpResponse response = client.execute(request);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() == 200 ) {
				return CosmFactory.toDatastream(client.getBody(response));
			} else {
				throw new HttpException(statusLine.toString());
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new CosmException(e.getMessage());
		}
	}
	
	// get datastreams
	public Datastream[] getDatastreams(Integer feedid) throws CosmException {
		try {
			Feed feed = getFeed(feedid);
			return feed.getDatastreams();
		} catch ( Exception e ) {
			throw new CosmException(e.getMessage());
		}

	}
	
	// create datastream
	public Datastream createDatastream(Integer feedid, Datastream datastream) throws CosmException {
		try {
			HttpPost request = new HttpPost("http://api.cosm.com/v2/feeds/"+feedid+"/datastreams.json");
			JSONObject jo = new JSONObject();
			jo.put("version", Cosm.VERSION);
			jo.append("datastreams", datastream.toJSONObject());
			request.setEntity(new StringEntity(jo.toString()));
			HttpResponse response = client.execute(request);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() == 201 ) {
				String a[] = response.getHeaders("Location")[0].getValue().split("/");
				String datastreamid = a[a.length -1];
				client.getBody(response);
				return this.getDatastream(feedid,datastreamid);
			} else {
				throw new HttpException(response.getStatusLine().toString());																
			}
		} catch ( Exception e) {
			e.printStackTrace();
			throw new CosmException("Caught exception in create Datastream" + e.getMessage());
		}
	}
		
	// update datastream
	public void updateDatastream(Integer feedid,String datastreamid, Datastream datastream) throws CosmException {
		try {
			HttpPut request = new HttpPut("http://api.cosm.com/v2/feeds/"+feedid+"/datastreams/"+datastreamid+".json");
			request.setEntity(new StringEntity(datastream.toJSONObject().toString()));
			HttpResponse response = client.execute(request);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() == 200 ) {
				client.getBody(response);
				return;
			} else {
				throw new HttpException(statusLine.toString());
			}
		} catch ( Exception e ) {
			throw new CosmException(e.getMessage());
		}
	}
	
	// delete datastream
	public void deleteDatastream(Integer feedid,String datastreamid) throws CosmException {
		try {
			HttpDelete hr = new HttpDelete("http://api.cosm.com/v2/feeds/"+feedid+"/datastreams/"+ datastreamid);
			HttpResponse response = client.execute(hr);
			StatusLine statusLine = response.getStatusLine();
			if ( statusLine.getStatusCode() != 200 ) {
				throw new CosmException(response.getStatusLine().toString());																				
			}
		} catch ( Exception e) {		
			e.printStackTrace();
			throw new CosmException(e.getMessage());
		}		
	}
	
	
	// create datapoint
	public void createDatapoint(Integer feedid,String datastreamid,Datapoint datapoint) throws CosmException {
		try {
			HttpPost request = new HttpPost("http://api.cosm.com/v2/feeds/"+feedid+"/datastreams/"+datastreamid+"/datapoints.json");
			JSONObject jo = new JSONObject();
			jo.append("datapoints", datapoint.toJSONObject());
			request.setEntity(new StringEntity(jo.toString()));
			HttpResponse response = client.execute(request);			
			StatusLine statusLine = response.getStatusLine();
			client.getBody(response);
			if ( statusLine.getStatusCode() != 200 ) {
				throw new CosmException(response.getStatusLine().toString());								
			}						
		} catch ( Exception e) {
			e.printStackTrace();
			throw new CosmException("Caught exception in create datapoint" + e.getMessage());
		}
			
	}
	
	// create datapoints
	public void createDatapoints(Integer feedid,String datastreamid,Datapoint[] datapoints) throws CosmException {
		try {
			HttpPost request = new HttpPost("http://api.cosm.com/v2/feeds/"+feedid+"/datastreams/"+datastreamid+"/datapoints.json");
			JSONObject jo = new JSONObject();
			for(int i=0;(i<datapoints.length);i++) {			
				jo.append("datapoints", datapoints[i].toJSONObject());
			}
			request.setEntity(new StringEntity(jo.toString()));
			HttpResponse response = client.execute(request);			
			StatusLine statusLine = response.getStatusLine();
			String body = client.getBody(response);
			if ( statusLine.getStatusCode() != 200 ) {
				JSONObject ej = new JSONObject(body);
				throw new CosmException(ej.getString("errors"));
			}						
		} catch ( Exception e) {
			System.err.println(e.getMessage());
			throw new CosmException("Caught exception in create datapoint" + e.getMessage());
		}
			
	}
	
	// update datapoint
	// Cosm documentation says, it's a post. It is in fact a PUT
	public void updateDatapoint(Integer feedid,String datastreamid,Datapoint datapoint) throws CosmException {
		try {
			HttpPut request = new HttpPut("http://api.cosm.com/v2/feeds/"+ feedid + "/datastreams/"+datastreamid+"/datapoints/"+datapoint.getAt() + ".json");
			JSONObject jo = new JSONObject();
			jo.put("value",datapoint.getValue());
			request.setEntity(new StringEntity(jo.toString()));
			HttpResponse response = client.execute(request);			
			StatusLine statusLine = response.getStatusLine();
			String body = client.getBody(response);
			if ( statusLine.getStatusCode() != 200 ) {
				System.err.println(body);
				if ( body.length() > 0 ) {
					JSONObject ej = new JSONObject(body);
					throw new CosmException(ej.getString("errors"));				
				} else {
					throw new CosmException(statusLine.toString());
				}
			}
		} catch ( Exception e) {
			throw new CosmException("Caught exception in update datapoint: " + e.getMessage());
		}
	}

	// get a datapoint
	
	// deleting a datapoint
	
	// deleting multiple datapoints

	
	// listing all datapoints, historical queries
	
	
	
	
	// apikey
	
	// trigger
	
	// user
	
	// permissions
	
	
}


//	
//	
//	private PachubeClient client;
//	public PachubeFeed Feed;
//	
//	public Pachube(String APIKEY) {
//		super();
//		this.Feed = new PachubeFeed();
//		client = new PachubeClient(APIKEY);
//	}
//	
//	public Pachube(String username,String password) {
//		super();
//		this.Feed = new PachubeFeed();
//		client = new PachubeClient(username,password);
//	}
//
//	
//	
//	
//
//
//	/**
//	 * This Method is not intended to be used by Users, instead get the Feed
//	 * object using getFeed() and create Datastreams from there, All changes
//	 * will be made to the online Feed.
//	 * 
//	 * @param feed
//	 * @param s
//	 * @return
//	 * @throws PachubeException
//	 */
////	public boolean createDatastream(int feed, String s) throws PachubeException {
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/api/feeds/"
////				+ feed + "/datastreams/");
////		hr.setMethod(HttpMethod.POST);
////		addAuthheader(hr);
////		hr.setBody(s);
////		HttpResponse g = client.send(hr);
////
////		if (g.getHeaderItem("Status").equals("HTTP/1.1 201 Created")) {
////			return true;
////		} else {
////			throw new PachubeException(g.getHeaderItem("Status"));
////		}
////	}
//
//	/**
//	 * This Method is not intended to be used by Users, instead get the Feed
//	 * object using getFeed() and delete Datastreams from there, All changes
//	 * will be made to the online Feed.
//	 * 
//	 * @param feed
//	 * @param datastream
//	 * @return
//	 */
////	public HttpResponse deleteDatastream(int feed, int datastream) {
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/api/feeds/"
////				+ feed + "/datastreams/" + datastream);
////		hr.setMethod(HttpMethod.DELETE);
////		addAuthheader(hr);
////		return client.send(hr);
////	}
//
//	/**
//	 * This Method is not intended to be used by Users, instead get the Feed
//	 * object using getFeed() and update Datastreams from there, All changes
//	 * will be made to the online Feed.
//	 * 
//	 * @param feed
//	 * @param datastream
//	 * @param s
//	 * @return
//	 */
////	public HttpResponse updateDatastream(int feed, int datastream, String s) {
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/api/feeds/"
////				+ feed + "/datastreams/" + datastream);
////		hr.setMethod(HttpMethod.PUT);
////		addAuthheader(hr);
////		hr.setBody(s);
////		System.out.println(hr.getHttpCommand());
////		return client.send(hr);
////	}
//
//	/**
//	 * This Method is not intended to be used by Users, instead get the Feed
//	 * object using getFeed() and get Datastreams from there.
//	 * 
//	 * @param feed
//	 * @param datastream
//	 * @return
//	 */
////	public HttpResponse getDatastream(int feed, int datastream) {
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/api/feeds/"
////				+ feed + "/datastreams/" + datastream + ".xml");
////		hr.setMethod(HttpMethod.GET);
////		addAuthheader(hr);
////		return client.send(hr);
////	}
////
//	/**
//	 * This Method is not intended to be used by Users, instead get the Feed
//	 * object using getFeed() and access Datastream history from there.
//	 * 
//	 * @param feed
//	 * @param datastream
//	 * @return
//	 */
////	public Double[] getDatastreamHistory(int feed, int datastream) {
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/feeds/" + feed
////				+ "/datastreams/" + datastream + "/history.csv");
////		hr.setMethod(HttpMethod.GET);
////		addAuthheader(hr);
////		String str = client.send(hr).getBody();
////		String[] arr = str.split(",");
////		Double[] arr1 = new Double[arr.length];
////		for (int i = 0; i < arr.length; i++) {
////			arr1[i] = Double.parseDouble(arr[1]);
////		}
////
////		return arr1;
////
////	}
//
//	/**
//	 * This Method is not intended to be used by Users, instead get the Feed
//	 * object using getFeed() and access Datastream archive from there.
//	 * 
//	 * @param feed
//	 * @param datastream
//	 * @return
//	 */
////	public String[] getDatastreamArchive(int feed, int datastream) {
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/feeds/" + feed
////				+ "/datastreams/" + datastream + "/archive.csv");
////		hr.setMethod(HttpMethod.GET);
////		addAuthheader(hr);
////		String str = client.send(hr).getBody();
////		return str.split("\n");
////
////	}
//
//	/**
//	 * Creates a Trigger on pachube from the object provided.
//	 * 
//	 * @param t
//	 * @return
//	 * @throws PachubeException
//	 */
////	public String createTrigger(Trigger t) throws PachubeException {
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/api/triggers");
////		hr.setMethod(HttpMethod.POST);
////		addAuthheader(hr);
////		hr.setBody(t.toString());
////		HttpResponse h = client.send(hr);
////		if (h.getHeaderItem("Status").equals("HTTP/1.1 201 Created")) {
////			return h.getHeaderItem("Location");
////		} else {
////			throw new PachubeException(h.getHeaderItem("Status"));
////		}
////
////	}
////	
//	/**
//	 * Gets a Trigger from pachube specified by the parameter
//	 * 
//	 * @param id id of the Trigger to get
//	 */
////	public Trigger getTrigger(int id) throws PachubeException {
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/api/triggers/"+id+".xml");
////		hr.setMethod(HttpMethod.GET);
////		addAuthheader(hr);
////		HttpResponse h = client.send(hr);
////		
////		return PachubeFactory.toTrigger(h.getBody());
////
////	}
//	
//	/**
//	 * Gets all the Triggers owned by the authenticating user
//	 * 
//	 * @param id id of the Trigger to get
//	 */
////	public Trigger[] getTriggers() throws PachubeException {
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/api/triggers/");
////		hr.setMethod(HttpMethod.GET);
////		addAuthheader(hr);
////		HttpResponse h = client.send(hr);
////		
////		return PachubeFactory.toTriggers(h.getBody());
////
////	}
//	
//	
//	
//	
//	public Group[] getGroups() throws PachubeException {
//		try {
//			return PachubeFactory.toGroups(client.httpGet("http://api.cosm.com/v2/groups.json"));				
//		} catch ( Exception e) {		
//			e.printStackTrace();
//			throw new PachubeException(e.getMessage());
//		}		
//	}
//
//	public Group getGroup(String groupid) throws PachubeException {
//		try {
//			return PachubeFactory.toGroup(client.httpGet("http://api.cosm.com/v2/groups/"+groupid+".json"));				
//		} catch ( Exception e) {		
//			e.printStackTrace();
//			throw new PachubeException(e.getMessage());
//		}		
//	}
//
//	public HttpResponse deleteGroup(String groupid) throws PachubeException {
//		try {
//			return client.httpDelete("http://api.cosm.com/v2/groups/"+groupid);				
//		} catch ( Exception e) {		
//			e.printStackTrace();
//			throw new PachubeException(e.getMessage());
//		}		
//	}
//
//	
////	public HttpResponse updateGroup(int id,Group g){
////		HttpRequest hr = new HttpRequest("http://api.cosm.com/v2/groups/"+id);
////		hr.setMethod(HttpMethod.PUT);
////		addAuthheader(hr);
////		
////		try {			
////			JSONObject jo = new JSONObject();
////			jo.putOpt("group_id", g.getGroupid());
////			jo.putOpt("label",g.getLabel());
////			for(int i=0;(i<g.getFeeds().length);i++) {
////				jo.accumulate("feeds",g.getFeeds()[i]);
////			}
////			for(int j=0;(j<g.getMembers().length);j++) {
////				jo.accumulate("members",g.getFeeds()[j]);
////			}
////			hr.setBody(jo.toString());
////		} catch ( Exception e ) {
////			
////		}
////		return client.send(hr);		
////	}
////
////	
////	/**
////	 * Deletes a Trigger from pachube
////	 * @param id id of the trigger to delete
////	 * @return
////	 */
////	public HttpResponse deleteTrigger(int id){
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/api/triggers/"+id);
////		hr.setMethod(HttpMethod.DELETE);
////		addAuthheader(hr);
////		return client.send(hr);
////		
////	}
////	
////	/**
////	 * Updates a Trigger on pachube
////	 * @param id id of the triggerto update
////	 * @param t Trigger object of the new trigger
////	 * @return
////	 */
////	public HttpResponse updateTrigger(int id,Trigger t){
////		HttpRequest hr = new HttpRequest("http://www.pachube.com/api/triggers/"+id);
////		hr.setMethod(HttpMethod.PUT);
////		addAuthheader(hr);
////		hr.setBody(t.toString());
////		return client.send(hr);
////		
////	}
////
////	/**
////	 * Gets a Pachube graph of the datastream
////	 * 
////	 * @param feedID
////	 *            ID of feed the datastream belongs to.
////	 * @param streamID
////	 *            ID of the stream to graph
////	 * @param width
////	 *            Width of the image
////	 * @param height
////	 *            Height of the image
////	 * @param c
////	 *            Color of the line
////	 * @return String which can be used to form a URL Object.
////	 */
////	public String showGraph(int feedID, int streamID, int width, int height,
////			Color c) {
////		String hexRed = Integer.toHexString(c.getRed()).toString();
////		String hexGreen = Integer.toHexString(c.getGreen()).toString();
////		String hexBlue = Integer.toHexString(c.getBlue()).toString();
////		if (hexRed.length() == 1) {
////			hexRed = "0" + hexRed;
////		}
////
////		if (hexGreen.length() == 1) {
////			hexGreen = "0" + hexGreen;
////		}
////		if (hexBlue.length() == 1) {
////			hexBlue = "0" + hexBlue;
////		}
////		String hex = (hexRed + hexGreen + hexBlue).toUpperCase();
////
////		return "http://www.pachube.com/feeds/" + feedID + "/datastreams/"
////				+ streamID + "/history.png?w=" + width + "&h=" + height + "&c="
////				+ hex;
////
////	}
//
//}