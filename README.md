# DetailEase - Personal Details Management App

DetailEase is an Android application designed to help users manage and edit personal details, such as names, ages, and cities, with ease. The app is built using Kotlin and Jetpack libraries and uses Room for local data storage. 

## Features

- **Add, Edit, Delete:** Seamlessly manage personal details.
- **Search Functionality:** Quickly filter and find specific entries.
- **Bottom Sheet Dialog:** Smooth and intuitive UI for adding and editing details.
- **RecyclerView with ListAdapter:** Efficient data display and binding.
- **LiveData Integration:** Real-time updates of the UI based on data changes.
- **Room Database:** Local storage solution for persistent data management.

## Screenshots

*(Add screenshots of the app's interface here)*

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/YourUsername/DetailEase.git
Open the project in Android Studio.

Build the project and run it on an Android emulator or a physical device.

Technologies Used
Kotlin: For Android development.
Room Database: To store and manage data locally.
RecyclerView with ListAdapter: For displaying the list of details with efficient data handling.
LiveData: To observe and react to changes in data.
Coroutines: To handle asynchronous operations smoothly.
BottomSheetDialogFragment: For an improved UI/UX when adding/editing details.
Code Overview
MainActivity.kt
Handles UI interactions such as searching, adding, and deleting personal details.
Sets up RecyclerView with PersonDetailsAdapter to display the list of persons.
Observes LiveData to update the UI when search queries change.
AddEditPersonFragment.kt
Manages the BottomSheetDialog for adding or editing a person's details.
Validates input before saving or updating the data in the Room database.
PersonDetailsAdapter.kt
Displays each person's details in the RecyclerView.
Handles click events for editing and deleting items.
Contributing
Contributions are welcome! Feel free to open issues or submit pull requests to improve the app.

License
This project is licensed under the MIT License - see the LICENSE file for details.
