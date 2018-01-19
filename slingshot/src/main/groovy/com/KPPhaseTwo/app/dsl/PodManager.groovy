package com.KPPhaseTwo.app.dsl

import com.KPPhaseTwo.app.pages.pods.SearchPodPage
import com.KPPhaseTwo.app.pages.pods.RecommendedTrainingPage
import com.KPPhaseTwo.app.pages.pods.BuyAPodPage
import com.KPPhaseTwo.app.pages.pods.FiltersPage
import com.KPPhaseTwo.app.pages.pods.PodWishlistPage
import com.KPPhaseTwo.app.pages.pods.OngoingTrainingPage
import com.KPPhaseTwo.app.pages.admins.FollowedOrganizationsListPage
import com.KPPhaseTwo.app.pages.admins.ManageUserPage
import com.KPPhaseTwo.app.pages.pods.PodCustomizationPage
import com.KPPhaseTwo.app.pages.pods.OngoingPodsPage
import com.KPPhaseTwo.app.pages.pods.PodDetailsPage

/**
 * Created by Samir on 21/02/2017
 */
class PodManager {

	//To verify that the pod list is displayed on clicking search button
	def podsDisplayed = { browser, formData ->
		SearchPodPage.podsDisplayed browser, formData
	}


	//To verify that the result displayed for the serached pods are shown correctly
	def correctPodsDisplayed = { browser, formData ->
		SearchPodPage.correctPodsDisplayed browser, formData
	}

	//To verify that the pod name displayed in the pod details is same as the pod clicked
	def podName = { browser, formData ->
		SearchPodPage.podName browser, formData
	}


	//To add the first pod to wishlist
	def addToWishlist = { browser, formData ->
		SearchPodPage.addToWishlist browser, formData
	}

	//To verify that the wishlisted pod is displayed in wishlist page
	def wishlistPodDsplyd = { browser, formData ->
		SearchPodPage.wishlistPodDsplyd browser, formData
	}

	//To remove the first pod to wishlist
	def removeFrmWshList = { browser, formData ->
		SearchPodPage.removeFrmWshList browser, formData
	}

	//To verify that the wishlisted pod is removed from the pod wishlist page
	def wishListedPodRemoved = { browser, formData ->
		SearchPodPage.wishListedPodRemoved browser, formData
	}

	//To verify result shown per page is the same number pods displayed per page
	def resultDisplayedPerPage = { browser, formData ->
		SearchPodPage.resultDisplayedPerPage browser, formData
	}

	//To verify the number of pods in Recommended training page
	def verifyPodsInRecommended = { browser, formData ->
		RecommendedTrainingPage.verifyPodsInRecommended browser, formData
	}

	//To verify number per page on click on Items per page
	def podsAndRulerNoMatch = { browser, formData ->
		RecommendedTrainingPage.podsAndRulerNoMatch browser, formData
	}

	//To verify wishlisted pod displayed in the wishlist page
	def wishPodDisplayed = {browser, formData ->
		RecommendedTrainingPage.wishPodDisplayed browser,  formData
	}

	//To verify clicking on a pod from the list
	def clickPodToBuy= {browser, formData ->
		RecommendedTrainingPage.clickPodToBuy browser, formData
	}

	//Using Filters
	//To Verify the selected Skill filter are matching in the result set.
	def displayedSkillFilter = { browser, formData ->
		FiltersPage.displayedSkillFilter browser, formData
	}

	//to verify the selected industry from filter are matching in the result set.
	def displayedIndustryFilter = { browser, formData ->
		FiltersPage.displayedIndustryFilter browser, formData
	}


	//to verify the selected level from filter are matching in the result set.
	def static displayedLevelFilter = {browser, formData ->
		FiltersPage.displayedLevelFilter browser, formData
	}

	//to verify the selected minimum and max duration are matching or not in the result set.
	def static displayedMinAndMaxDurationFilter = {browser, formData ->
		FiltersPage.displayedMinAndMaxDurationFilter browser, formData
	}

	//to verify the selected sortby dropdown are displaying or not in the result set.
	def static displayedSortByFilter = {browser, formData ->
		FiltersPage.displayedSortByFilter browser, formData
	}

	//to verify the selected skill is removed and also verify the count of pods.
	def static removeSelectedSkills = {browser,formData ->
		FiltersPage.removeSelectedSkills browser, formData
	}

	//To get the amount of the pod from pod details page
	def static getCostOfPod = {browser, formData ->
		PodDetailsPage.getCostOfPod browser, formData
	}

	//To verify buying a pod
	def static podBuyRequest = {browser, formData ->
		PodDetailsPage.podBuyRequest browser, formData
	}

	//To verify the pod amount in purchase request page with pod details amount
	def static equalAmountStatus = {browser, formData ->
		BuyAPodPage.equalAmountStatus browser, formData
	}

	//To verify the number of users
	def static noOfUsersEntered = {browser, formData ->
		BuyAPodPage.noOfUsersEntered browser, formData
	}

	def static getNoOfPeopleInPods = {browser, formData ->
		OngoingTrainingPage.getNoOfPeopleInPods browser, formData
	}

	def static addingPodsToWishList = { browser, formData ->
		PodWishlistPage.addingPodsToWishList browser, formData
	}

	def static isPodWishlistDisplayed = { browser, formData ->
		PodWishlistPage.isPodWishlistDisplayed browser, formData
	}

	def static removeAddedPodWishlist = { browser, formData ->
		PodWishlistPage.removeAddedPodWishlist browser, formData
	}

	def static removeAddedPodWishlistOngoing = {browser, formData ->
		OngoingTrainingPage.removeAddedPodWishlistOngoing browser, formData
	}


	//Followed Organization List
	def static isErrorMessageCorrect = {browser, formData ->
		FollowedOrganizationsListPage.isErrorMessageCorrect browser, formData
	}

	def static organizationsDisplayed = {browser, formData ->
		FollowedOrganizationsListPage.organizationsDisplayed browser, formData
	}

	def static sameFilterValuesAsSearch = {browser, formData ->
		FollowedOrganizationsListPage.sameFilterValuesAsSearch browser, formData
	}

	def static correctOrgDisplayed = {browser, formData ->
		FollowedOrganizationsListPage.correctOrgDisplayed browser, formData
	}

	def static correctOrgProfile = {browser,formData ->
		FollowedOrganizationsListPage.correctOrgProfile browser,formData
	}

	def static isCorrectLocationList = {browser, formData ->
		FollowedOrganizationsListPage.isCorrectLocationList browser, formData
	}

	def static isCorrectIndustryList = {browser, formData ->
		FollowedOrganizationsListPage.isCorrectIndustryList browser, formData
	}

	def static isCorrectSortingList = {browser, formData ->
		FollowedOrganizationsListPage.isCorrectSortingList browser, formData
	}

	def static addToFollowedOrgPage ={browser, formData ->
		FollowedOrganizationsListPage.addToFollowedOrgPage browser, formData
	}

	def static followedOrgDsplyd ={browser, formData ->
		FollowedOrganizationsListPage.followedOrgDsplyd browser, formData
	}

	def static removeFrmFollowedList ={browser, formData ->
		FollowedOrganizationsListPage.removeFrmFollowedList browser, formData
	}

	def static isFollowedOrgRemoved ={browser, formData ->
		FollowedOrganizationsListPage.isFollowedOrgRemoved browser, formData
	}

	def static followOrg ={browser, formData ->
		FollowedOrganizationsListPage.followOrg browser, formData

	}

	//Manage Users

	def static isManageUsrErrorMessageCorrect = {browser, formData ->
		ManageUserPage.isManageUsrErrorMessageCorrect browser,formData
	}

	def static clickOperation = {browser,formData ->
		ManageUserPage.clickOperation browser,formData
	}

	def static correctUserProfile = {browser, formData ->
		ManageUserPage.correctUserProfile browser, formData
	}

	def static removeUser = {browser,formData ->
		ManageUserPage.removeUser browser, formData
	}

	def static loginAsRemovedUser = {browser, formData ->
		ManageUserPage.loginAsRemovedUser browser, formData
	}

	def static manageUserSortByFilter = {browser, formData ->
		ManageUserPage.manageUserSortByFilter browser, formData
	}


	//Pod Customizations

	def static podsAvailability = {browser, formData ->
		PodCustomizationPage.podsAvailability browser, formData
	}

	//to check whether pod icon is uploaded or not
	def static podIconUpload = {browser, formData ->
		PodCustomizationPage.podIconUpload browser, formData
	}

	//Ongoing pods
	//to get the first pod name
	def static getFirstPodName = {browser, formData ->
		OngoingPodsPage.getFirstPodName browser, formData
	}

	def static correctPodProgressPage = {browser, formData ->
		OngoingPodsPage.correctPodProgressPage browser, formData
	}

	//Pod Details page
	//to verify Buy button and Enter classroom button is available
	def static isBuyAndEnterClassroomButtons ={browser, formData ->
		PodDetailsPage.isBuyAndEnterClassroomButtons browser, formData
	}

	//to verify Forum button and Renew Licenses button is displayed
	def static forumAndEnterClsRmButtonDisplayed = { browser, formData ->
		PodDetailsPage.forumAndEnterClsRmButtonDisplayed browser, formData
	}

	//to check Enter Classroom button is Enabled or not
	def static checkEnterClsroomBtnEnabled = {browser, formData ->
		PodDetailsPage.checkEnterClsroomBtnEnabled browser, formData
	}

	//to check wheather a pod is valid
	def static podIsUnderValidLicense = {browser, formData ->
		PodDetailsPage.podIsUnderValidLicense browser, formData
	}
}
