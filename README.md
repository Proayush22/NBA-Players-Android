# 🏀 NBA Player Stats Selector — Android App

An interactive Android application that displays NBA players and their 2022–23 stats.
The app supports portrait and landscape modes, allows users to select a player from a list, and dynamically updates the UI with detailed player information.

## 📱 Features
## 🔹 Player List

<img width="300" height="2400" alt="image" src="https://github.com/user-attachments/assets/ebe71ccf-2633-4556-ad6b-a4c719c1e06e" />
<img width="300" height="2400" alt="image" src="https://github.com/user-attachments/assets/fcf162fb-6474-45ad-bfc3-7626ac7968bc" />
<img width="500" height="1080" alt="image" src="https://github.com/user-attachments/assets/53bcd846-2694-4072-8595-a6e12688c5d2" />
<img width="500" height="1080" alt="image" src="https://github.com/user-attachments/assets/e9384ea7-f4b2-4923-9f63-ab2fee9fab25" />


Scrollable list of NBA players with:

Name

Team

Position

Player photo

## 🔹 Player Detail Panel

Selecting a player reveals:

Height

Weight

Age

Points per game

Rebounds

Assists

Steals

Blocks

Turnovers

Shooting percentages

Games played

Displayed instantly with no lag.

## 🔹 Selection Toggle

Tapping a player selects them and loads stats

Tapping the same player again deselects them and returns the “Fantasy Scoring Guide”

## 🔹 Landscape Mode Support

In landscape, the app shows the guide or selected player stats persistently, side-by-side

When rotated, the selected player persists due to saved-instance state handling

## 🧠 How It Works
✔ Parcelable Player Model

Each Player object is passed and saved using a fully implemented Parcelable model:

ID

Name

Team

Position

Player image resource

Individual stat strings

✔ ListView Adapter

A custom PlayerAdapter binds player data to list items.

✔ State Persistence

The app stores:

The current player list

The name of the selected player

via:

onSaveInstanceState()
onCreate(savedInstanceState)


ensuring UI continuity after rotation.

✔ Dynamic UI Updates

All stats update instantly on selection, using reusable helper methods such as:

showStats(Player p)
showFantasyGuide()


for cleaner code and reduced duplication.


## 🏗 Technologies Used

Java

Android SDK

ListView + Custom Adapter

Parcelable Data Passing

ConstraintLayout & XML UI

Resource Management (drawables, strings, layouts)
