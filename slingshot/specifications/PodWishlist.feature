Feature: Successful and Unsuccessful Pod Wishlist functionality to the application
As an user of the application
I want to test Pod wishlist features

Background: Login:loginSuccess


Scenario: To verify clicking on Pod Wishlist from left menu navigates to Pod wishlist page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page


Scenario: To wishlist a pod and verify the pod is displaying in the wishlist page with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodButton
	And I PERFORM addingPodsToWishList to add few pods to wishlist page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	And I VERIFY isPodWishlistDisplayed in wishlist page
	Then the wishlisted pod is displayed in the wishlist page
	Then I am ON podWishList page


Scenario: To remove a wishlist and verify that the wishlist has been removed with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	When I CLICK searchPodButton
	And I PERFORM addToWishlist to add the first pod to wishlist
	And I PERFORM removeFrmWshList to remove the first pod to wishlist
	And I CLICK dashBoardLink to navigate to dashboard page
	Then I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	And I VERIFY wishListedPodRemoved
	Then the wishlisted pod is removed from the page

Scenario: To verify the number of pods displayed in each page is matching the number shown with DATA PodSearch_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	When I CLICK searchPodButton
	Then I am ON searchPods page
	And I VERIFY resultDisplayedPerPage
	Then the result shown per page is the same number pods displayed per page

Scenario: To verify the Search Pod landing page from dashboard
	Given I am ON dashboard page
	When I CLICK dashboardSearchPodBtn button from dashboard
	Then I am ON searchPods page

Scenario: To verify the result displayed for skill filter is displaying correctly with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodButton
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails in skills
	And I VERIFY displayedSkillFilter result set
	Then the filtered pod name displayed are same as the skills selected

Scenario: To verify adding pods to Pod Wishlist page from search pod page and test industry filter with DATA PodIndustryFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in Industry
	And I VERIFY displayedIndustryFilter result set
	Then the filtered pod name is same as the pod clicked
	


Scenario: To verify the result displayed for organization filter is correct with DATA PodOrganizationFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodButton
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails in organization
	And I VERIFY displayedOrgFilter result set
	Then the filtered pod name is same as the pod clicked 

Scenario: To verify the result displayed for min and max filter is correct with DATA PodLevelFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in Industry
	And I VERIFY displayedLevelFilter result set
	Then the filtered pod name is same as the pod clicked
	
		
Scenario: To verify the result displayed for min and max filter is correct with DATA PodMinMaxFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in min And max duration
	And I VERIFY displayedMinAndMaxDurationFilter result set
	Then the filtered pod name is same as the pod clicked


Scenario: To verify adding pods to Pod Wishlist page from search pod page and sort by filter with DATA PodSortByFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in sortBy 
	And I VERIFY displayedSortByFilter result set
	Then the filtered pod name is same as the pod clicked


Scenario: To verify the pod count displayed is same when we try to select and remove the same item with DATA PodSkillFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I ENTER filterDetails in skills 
	And I VERIFY removeSelectedSkills from the auto suggest
	Then the Pod count is correct
	
	
Scenario: To verify the pods are successfully removed from pod wishlist page
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK podWishlist
	Then I am ON podWishList page
	When I try to remove all the pods from pod wishlist page
	And I VERIFY removeAddedPodWishlist from the pod wishlist page
	Then pod wishlist page is not displaying any pods
	Then the Pod count should be correct

Scenario: To verify the pod count displayed is same when we try to select and remove the same Organization with DATA PodOrganizationFilter_Success
	Given I am ON dashboard page
	When I CLICK leftMenuButton
	And I CLICK searchPods link
	Then I am ON searchPods page
	And I CLICK searchPodButton
	When I VERIFY podsDisplayed
	Then all the pods are displayed
	When I ENTER filterDetails in organization autosuggest
	And I VERIFY removeSelectedOrg from the auto suggest
	Then the Pod count should be correct

