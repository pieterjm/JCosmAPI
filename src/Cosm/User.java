package Cosm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class User {
	
	// not all fields are always available
	private Integer totalApiAccesCount;
	private String[] createableRoles;
	private Integer datastreamsCount;
	private String emailHash;
	private Boolean provisioningEnabled;
	private Integer feedsCount;
	private Integer datastreamsAllowed;
	private Integer dailyApiAccessCount;
	private UserState state;
	
	private Boolean displayStats;
	private Boolean displayActivity;
	private Boolean displayInformation;
	private Boolean deliverEmail;
	private Boolean receiveForumNotifications;
	private String organisation;
	private String about;
	private String website;
	private String fullName;
	private String timeZone;
	private Boolean subscribedToMailings;
	private String[] roles;
	private String login;
	private String email;

// these keys are described in the API bu seem obsolete
//	private String api_key;
//	private String firstName;
//	private String lastName;
	
	public JSONObject toJSONObject() throws JSONException {
		try {
			JSONObject jo = new JSONObject();

			jo.putOpt("display_stats", displayStats); // != null ? displayStats.toString() : null);
			jo.putOpt("display_activity", displayActivity); // != null ? display_activity.toString() : null);
			jo.putOpt("display_information", displayInformation); // != null ? display_information.toString() : null);
			jo.putOpt("deliver_mail", deliverEmail); // != null ? deliver_email.toString() : null);
			jo.putOpt("receive_forum_notifications", receiveForumNotifications); // != null ? receiveForumNotifications.toString() : null);
			jo.putOpt("organisation", organisation);
			jo.putOpt("about", about);
			jo.putOpt("website", website);
			jo.putOpt("full_name", fullName);
			jo.putOpt("time_zone",timeZone);
			jo.putOpt("subscribed_to_mailings", subscribedToMailings); // != null ? subscribedToMailings.toString() : null);
			JSONArray ja = new JSONArray();
			for(int i=0;(i<roles.length);i++) {
				ja.put(roles[i]);
			}
			jo.put("roles", ja);
			jo.putOpt("login", login);
			jo.putOpt("email", email);
			
		
			JSONObject jou = new JSONObject();
			jou.put("user",jo);
			
			return jou;
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new JSONException(e.getMessage());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
//	public String getApiKey() {
//		return api_key;
//	}
//	public void setApiKey(String api_key) {
//		this.api_key = api_key;
//	}
	public String[] getCreateableRoles() {
		return createableRoles;
	}
	public void setCreateableRoles(String[] createable_roles) {
		this.createableRoles = createable_roles;
	}
	public Boolean getDeliverEmail() {
		return deliverEmail;
	}
	public void setDeliverEmail(Boolean deliver_email) {
		this.deliverEmail = deliver_email;
	}
	public Boolean getDisplayActivity() {
		return displayActivity;
	}
	public void setDisplayActivity(Boolean display_activity) {
		this.displayActivity = display_activity;
	}
	public Boolean getDisplayInformation() {
		return displayInformation;
	}
	public void setDisplayInformation(Boolean display_information) {
		this.displayInformation = display_information;
	}
//	public String getFirstName() {
//		return firstName;
//	}
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//	public String getLastName() {
//		return lastName;
//	}
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getDisplayStats() {
		return displayStats;
	}
	public void setDisplayStats(Boolean displayStats) {
		this.displayStats = displayStats;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Boolean getReceiveForumNotifications() {
		return receiveForumNotifications;
	}
	public void setReceiveForumNotifications(Boolean receiveForumNotifications) {
		this.receiveForumNotifications = receiveForumNotifications;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	public Boolean getSubscribedToMailings() {
		return subscribedToMailings;
	}
	public void setSubscribedToMailings(Boolean subscribedToMailings) {
		this.subscribedToMailings = subscribedToMailings;
	}
	
	
	
	
	public Integer getTotalApiAccesCount() {
		return totalApiAccesCount;
	}












	public void setTotalApiAccesCount(Integer totalApiAccesCount) {
		this.totalApiAccesCount = totalApiAccesCount;
	}












	public Integer getDatastreamsCount() {
		return datastreamsCount;
	}












	public void setDatastreamsCount(Integer datastreamsCount) {
		this.datastreamsCount = datastreamsCount;
	}












	public String getEmailHash() {
		return emailHash;
	}












	public void setEmailHash(String emailHash) {
		this.emailHash = emailHash;
	}












	public Boolean getProvisioningEnabled() {
		return provisioningEnabled;
	}












	public void setProvisioningEnabled(Boolean provisioningEnabled) {
		this.provisioningEnabled = provisioningEnabled;
	}












	public Integer getFeedsCount() {
		return feedsCount;
	}












	public void setFeedsCount(Integer feedsCount) {
		this.feedsCount = feedsCount;
	}












	public Integer getDatastreamsAllowed() {
		return datastreamsAllowed;
	}












	public void setDatastreamsAllowed(Integer datastreamsAllowed) {
		this.datastreamsAllowed = datastreamsAllowed;
	}












	public Integer getDailyApiAccessCount() {
		return dailyApiAccessCount;
	}












	public void setDailyApiAccessCount(Integer dailyApiAccessCount) {
		this.dailyApiAccessCount = dailyApiAccessCount;
	}












	public UserState getState() {
		return state;
	}












	public void setState(UserState state) {
		this.state = state;
	}












	public User() {
		this.roles = new String[0];
		this.createableRoles = new String[0];
		this.state = null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
