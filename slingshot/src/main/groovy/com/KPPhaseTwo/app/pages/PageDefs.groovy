package com.KPPhaseTwo.app.pages

import groovy.transform.ToString;

import com.KPPhaseTwo.tools.Browser
import com.KPPhaseTwo.app.pages.KPCommonPage

class PageDefs {
	
	private static List<PageDefEntry> pageDefEntries = new ArrayList();
	static {
		// Define the Pages available...  (page-key, page-name, page-className)		
			
		pageDefEntries.add( new PageDefEntry("home", "POD.WORKS", null))	
		pageDefEntries.add( new PageDefEntry("login", "Login", "com.KPPhaseTwo.app.pages.user.LoginPage"))
		pageDefEntries.add( new PageDefEntry("bifurcateUser", "Bifurcate User", null))
		pageDefEntries.add( new PageDefEntry("companyRegistration", "Company Registration", "com.KPPhaseTwo.app.pages.user.CompanyRegistrationPage"))
		
		//User
		pageDefEntries.add( new PageDefEntry("dashboard", "Dashboard", "com.KPPhaseTwo.app.pages.user.DashboardPage"))
		pageDefEntries.add( new PageDefEntry("individaulRegister", "Register User", "com.KPPhaseTwo.app.pages.user.IndividualRegisterPage"))
		
		//Forgot password
		pageDefEntries.add( new PageDefEntry("forgotPassword", "Forgot Password", "com.KPPhaseTwo.app.pages.user.ForgotPasswordPage"))
		
		//manage Admins
		pageDefEntries.add( new PageDefEntry("manageAdmins", "Manage Admins", "com.KPPhaseTwo.app.pages.admins.ManageAdminsPage"))
		pageDefEntries.add( new PageDefEntry("addAdmin", "Add Admin", "com.KPPhaseTwo.app.pages.admins.AddAdminPage"))
		
		//Change password
		pageDefEntries.add( new PageDefEntry("changePassword", "Change Password", "com.KPPhaseTwo.app.pages.user.ChangePasswordPage"))
		
		//Edit Organization Profile
		pageDefEntries.add( new PageDefEntry("editOrganizationProfile", "Edit Organization Profile", "com.KPPhaseTwo.app.pages.admins.EditOrganizationProfilePage"))
		
		//View Organization Profile
		pageDefEntries.add( new PageDefEntry("viewOrganizationProfile", "Organization Profile", "com.KPPhaseTwo.app.pages.admins.ViewOrganizationProfilePage"))
		
		//Search Pods
		pageDefEntries.add( new PageDefEntry("searchPods", "Search Pods", "com.KPPhaseTwo.app.pages.pods.SearchPodPage"))
		
		//Pod details page
		pageDefEntries.add( new PageDefEntry("podDetails", "Pod Details", "com.KPPhaseTwo.app.pages.pods.PodDetailsPage"))
		
		//Pod Wishlist
		pageDefEntries.add( new PageDefEntry("podWishList", "Pods Wishlist", "com.KPPhaseTwo.app.pages.pods.PodWishlistPage"))
		
		//Terms and Privacy
		pageDefEntries.add( new PageDefEntry("privacyPolicy", "Privacy Policy", null))
		pageDefEntries.add( new PageDefEntry("termsOfUse", "Terms of Use", null))
		pageDefEntries.add( new PageDefEntry("aboutUs", "About Us", null))
		pageDefEntries.add( new PageDefEntry("contactUs", "Contact Us", null))
		
		//Recommended Training
		pageDefEntries.add( new PageDefEntry("recommendedTraining", "Recommended Training", "com.KPPhaseTwo.app.pages.pods.RecommendedTrainingPage"))
		
		//Ongoing Training
		pageDefEntries.add( new PageDefEntry("ongoingTraining", "Ongoing Training", "com.KPPhaseTwo.app.pages.pods.OngoingTrainingPage"))
		
		//Change Privlages page
		pageDefEntries.add( new PageDefEntry("changePrivilages", "Change privileges", "com.KPPhaseTwo.app.pages.admins.ChangePrivilagesPage"))
		
		//Post Job
		pageDefEntries.add( new PageDefEntry("postJob","Job Post","com.KPPhaseTwo.app.pages.admins.PostJobPage"))
		pageDefEntries.add( new PageDefEntry("jobPostList","Job Postings List","com.KPPhaseTwo.app.pages.admins.PostJobPage"))
		
		//User Edit Profile
		pageDefEntries.add( new PageDefEntry("userViewProfile","View Public Profile", "com.KPPhaseTwo.app.pages.user.ViewUserPublicProfilePage"))
		pageDefEntries.add( new PageDefEntry("editProfile", "Edit Profile", "com.KPPhaseTwo.app.pages.user.UserEditProfilePage"))

		//Purchase Details Page
		pageDefEntries.add ( new PageDefEntry("paymentRequest","Payment Request", "com.KPPhaseTwo.app.pages.pods.BuyAPodPage"))
		
		//Sales Admin License Details Page
		pageDefEntries.add( new PageDefEntry("LicenseDetails", "License Details", "com.KPPhaseTwo.app.pages.admins.SalesAdminLicenseDetailsPage"))

		//Followed Organization List
		pageDefEntries.add( new PageDefEntry("followedOrganizationList","View Followed Organizations",null))
		pageDefEntries.add( new PageDefEntry("searchOrganizations","Search List Of Organizations","com.KPPhaseTwo.app.pages.admins.FollowedOrganizationsListPage"))
		pageDefEntries.add( new PageDefEntry("viewFollowedOrg","View Followed Organizations",null))
		
		//Manage Users
		pageDefEntries.add( new PageDefEntry("manageUser","Manage Users","com.KPPhaseTwo.app.pages.admins.ManageUserPage"))
		pageDefEntries.add( new PageDefEntry("participantList","Participate List",null))
		pageDefEntries.add( new PageDefEntry("inviteUser","Invite User",null))
		
		
		//Pod Customization 
		pageDefEntries.add( new PageDefEntry("podCustomization","Pods Customization","com.KPPhaseTwo.app.pages.pods.PodCustomizationPage"))
		
		
	}
	
	//Get Key for the current page
	public static getCurrentPageKey(def browser){
		def pageName
		
		pageName = browser.getTitle()		
		 		 
		println "Page Name :" +pageName
		return findKeyByPageName(pageName.trim())
	}	
	
	
	/* Get PageDefEntry object for specified key */
	public static PageDefEntry getPageDefEntry (def pageKey) {
		for(PageDefEntry pageDefEntry : pageDefEntries) {
			if(pageDefEntry.key.equalsIgnoreCase(pageKey))
				return pageDefEntry;
		}
		return null;
	}
	
	private static findKeyByPageName(String pageName){		
		for(PageDefEntry pageDefEntry : pageDefEntries) {
			if(pageDefEntry.name.equalsIgnoreCase(pageName))
				return pageDefEntry.key;
		}
		return null;		
	}
}
