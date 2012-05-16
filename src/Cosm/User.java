package Cosm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private String about;
	private String api_key;
	private String[] createable_roles;
	private Boolean deliver_email;
	private Boolean display_activity;
	private Boolean display_information;
	private Boolean displayStats;
	private String email;
	private String firstName;
	private String lastName;
	private String login;
	private String organisation;
	private String timeZone;
	private String website;
	private String fullName;
	private Boolean receiveForumNotifications;
	private String[] roles;
	private Boolean subscribedToMailings;
	
	public JSONObject toJSONObject() throws JSONException {
		try {
			JSONObject jo = new JSONObject();
			
			jo.putOpt("login", login);
			jo.putOpt("full_name", fullName);
			jo.putOpt("email", email);
			jo.putOpt("website", website);
			jo.putOpt("about", about);
			jo.putOpt("organisation", organisation);
			jo.putOpt("deliver_email", deliver_email); // != null ? deliver_email.toString() : null);
			jo.putOpt("display_activity", display_activity); // != null ? display_activity.toString() : null);
			jo.putOpt("display_information", display_information); // != null ? display_information.toString() : null);
			jo.putOpt("display_stats", displayStats); // != null ? displayStats.toString() : null);
			jo.putOpt("receive_forum_notifications", receiveForumNotifications); // != null ? receiveForumNotifications.toString() : null);
			jo.putOpt("subscribed_to_mailings", subscribedToMailings); // != null ? subscribedToMailings.toString() : null);
			jo.putOpt("time_zone",timeZone);
			
			JSONArray ja = new JSONArray();
			for(int i=0;(i<roles.length);i++) {
				ja.put(roles[i]);
			}
			jo.put("roles", ja);
			jo.putOpt("first_name", firstName);
			jo.putOpt("last_name", lastName);
			
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
	public String getApiKey() {
		return api_key;
	}
	public void setApiKey(String api_key) {
		this.api_key = api_key;
	}
	public String[] getCreateableRoles() {
		return createable_roles;
	}
	public void setCreateableRoles(String[] createable_roles) {
		this.createable_roles = createable_roles;
	}
	public Boolean getDeliverEmail() {
		return deliver_email;
	}
	public void setDeliverEmail(Boolean deliver_email) {
		this.deliver_email = deliver_email;
	}
	public Boolean getDisplayActivity() {
		return display_activity;
	}
	public void setDisplayActivity(Boolean display_activity) {
		this.display_activity = display_activity;
	}
	public Boolean getDisplayInformation() {
		return display_information;
	}
	public void setDisplayInformation(Boolean display_information) {
		this.display_information = display_information;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
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
	
	
	
	public User() {
		this.roles = new String[0];
		this.createable_roles = new String[0];
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
