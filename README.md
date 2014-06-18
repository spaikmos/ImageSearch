# Week 2 Project:  Grid Image Search

Search for images and generate a grid view.

Time spent:  8 hours total.

Completed user stories:

 * [x] Required: User can enter a search query that will display a grid of image results from the Google Image API.
 * [x] Required: User can click on "settings" which allows selection of advanced search options to filter results
 * [x] Required: User can configure advanced search filters such as:
		Size (small, medium, large, extra-large)
		Color filter (black, blue, brown, gray, green, etc...)
		Type (faces, photo, clip art, line art)
		Site (espn.com)
 * [x] Required: Subsequent searches will have any filters applied to the search results
 * [x] Required: User can tap on any image in results to see the image full-screen
 * [x] Required: User can scroll down “infinitely” to continue loading more image results (up to 8 pages)
 * [ ] Advanced: Robust error handling, check if internet is available, handle error cases, network failures
 * [ ] Advanced: Use the ActionBar SearchView or custom layout as the query box instead of an EditText
 * [ ] Advanced: User can share an image to their friends or email it to themselves
 * [ ] Advanced: Replace Filter Settings Activity with a lightweight modal overlay
 * [ ] Advanced: Improve the user interface and experiment with image assets and/or styling and coloring
 * [ ] Bonus: Use the StaggeredGridView to display visually interesting image results
 * [ ] Bonus: User can zoom or pan images displayed in full-screen detail view

Release Notes:

I'm sorry.  This is the lamest Image Search app you will see.  I just had enough time to do the bare minimum, and nothing more.
Future apps will be better - I hope...

Implementation Notes:

Followed the video walkthrough to get majority of the app complete.

Added persistence to Settings activity to preserve settings selections.


Todo:

* All the Advanced and Bonus items.
* Use proper @+id's for all elements.
* Increase performance by caching view ID's.

Walkthrough of all user stories:

![Video Walkthrough](ImageSearchDemo.gif)