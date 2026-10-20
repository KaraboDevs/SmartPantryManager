Smart Pantry Manager

An Android application built in Java that helps users reduce food waste by tracking their pantry ingredients and suggesting recipes they can cook using strictly what they already have at home.

Features
- Pantry Management: Add, edit, and delete ingredients.
- Strict Recipe Matching: A recipe is only suggested if the user has *every* required ingredient in at least the required quantity (with plural normalization, e.g., "tomato" matches "tomatoes").
- Seeded Database: 15 pre-loaded recipes to start with.
- iOS-Inspired UI: Earthy color palette, rounded cards, and smooth bottom navigation.
- Persistent Settings: Toggle for expiring-soon alerts using SharedPreferences.

 Database Choice
I chose **SQLite** (via `SQLiteOpenHelper`) for the following reasons:
- Offline-first: The app functions completely offline, ensuring reliability for users without constant internet access.
- No backend required: It eliminates the need for a complex server setup, keeping the app lightweight.
- Perfect for the dataset: The structured, relational nature of recipes and ingredients fits perfectly into SQLite tables.

Setup & Run Instructions
1. Clone the repository: `git clone https://github.com/KaraboDevs/SmartPantryManager.git`
2. Open the project in **Android Studio**.
3. Let the Gradle sync complete.
4. Run the app on an emulator or physical device with **Android 7.0 (API 24)** or higher.
5. On first launch, the database will automatically create and seed 15 recipes.

Screens
- Pantry List
- Add/Edit Ingredient
- Suggested Recipes
- Settings
