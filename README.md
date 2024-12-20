# Sales and Traffic Statistics API

## Project Description

This is a Spring Boot RESTful API for managing sales and traffic statistics. The API updates the database from a file (`test_report.json`) every 5 minutes and caches the responses using Redis. It is designed to handle user authentication and provide secure access to the statistics.

## Technologies Used

- **Database:** MongoDB
- **Authentication:** Spring Security (with JWT support)
- **Caching:** Redis
- **Data Source:** JSON file `test_report.json`

## Features

- **User Registration:** Create a new user account.
- **User Authentication:** Obtain a JWT token to access secured endpoints.
- **Statistics by Date/Date Range:** Retrieve statistics for a specific date or date range (with caching).
- **Statistics by ASIN or List of ASINs:** Retrieve statistics for specific ASIN(s) (with caching).
- **Total Statistics:**
    - Across all dates.
    - Across all ASINs (with caching).
- **Periodic Updates:**
    - Refresh data from the file `test_report.json` every 5 minutes.
    - Only update database records if changes are detected in the file.

## Prerequisites

- **Java 17+**
- **Maven 3.6+**
- **MongoDB and Redis installed and running**
