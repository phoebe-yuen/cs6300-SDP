# Extra Requirements

**Author**: fyuen3 (Fung Yi Yuen)

* The application shall be run on Android phone with API level 28 or higher.
* The application shall maintain a local database (Android SQLite) to store and update job details.
* The application shall use a "MainMenu" page to contain all 4 functions "Current Job", "Job Offer", "Comparison Settings", and "Compare Jobs".
* All fields in the "Current Job" page and "Job Offer" page are mandatory fields.
* "Comparison Setting" button is default disabled until at least 2 jobs (including current job) saved in the database.
* Job ranking in the "Compare Jobs" page is based on the score calculated from the weighted sum of: 
[AYS + AYB + (RBP * AYS) + (LT * AYS / 260) - (CT * AYS / 8)](/GroupProject/Design-Team/design-description.md)<sup>formula details refer to Part 6 in [design-description.md](/GroupProject/Design-Team/design-description.md)</sup>
* User interface shall be intuitive and responsive 
