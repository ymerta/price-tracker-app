# Price Tracker
<img width="1000" alt="Ekran Resmi 2025-07-08 23 32 08" src="https://github.com/user-attachments/assets/c33b4126-10e3-4dec-8084-3b7fc53446f1" />
<img width="1000" alt="Ekran Resmi 2025-07-09 00 50 01" src="https://github.com/user-attachments/assets/1ebe30ee-01c4-4467-bc2a-29c87d6dcbbc" />
<img width="1000" alt="Ekran Resmi 2025-07-09 00 51 34" src="https://github.com/user-attachments/assets/dcc21a23-ada6-4129-9b49-45a6a57caab6" />

**Price Tracker** is a full-stack demo application that tracks product prices from various sources such as **Amazon**, **eBay**, **Idealo**, and **Otto**, using the [RapidAPI - Price Analytics API](https://rapidapi.com/letscrape-6bRBa3QguO5/api/price-analytics/).

Users can search for a product, fetch live pricing data, and sort results by price.

---

### Technologies Used

- **Spring Boot (Java)** – Backend REST API
- **WebClient (Reactive)** – For async HTTP calls to RapidAPI
- **RapidAPI** – Price Analytics API
- **Bootstrap 5** – UI design
- **Vanilla JavaScript + HTML** – For lightweight frontend (served via `src/main/resources/static/index.html`)

---

### Features

-  **Search by keyword** (e.g., "iPhone 13")
-  **Multi-source support**: Amazon, eBay, Idealo, Otto
-  **Auto-polling** every 3 seconds to fetch results
-  **Image + name + price** cards
-  **Sort all results by price** (across all sources)
-  **Clickable product cards** linking to product pages
-  **Filter out invalid items** (no price = skipped)
-  **Placeholder image** is used when no image is returned

---

### Project Structure

```
PriceTracker/
├── src/
│   └── main/
│       ├── java/com/ymerta/pricetracker/
│       │   ├── config/              # SecurityConfig (CORS etc.)
│       │   ├── controller/          # REST endpoints
│       │   ├── dto/                 # (Optional) Data transfer objects
│       │   ├── model/               # Product model
│       │   ├── repository/          # JPA Repository
│       │   └── service/             # RapidAPI integration logic
│       └── resources/
│           └── static/index.html    # Frontend UI (HTML+JS+Bootstrap)
├── .gitignore
├── pom.xml
└── README.md
```

---

### How to Run

#### 1. Start the backend

```bash
./mvnw spring-boot:run
```

This will serve the frontend on:

```
http://localhost:8080/index.html
```

#### 2. Usage

- Type a product name into the search bar
- Click **Search**
- Wait for results (auto-updates every 3 seconds)
- Click **"Sort All by Price"** to see all offers sorted
- Click on a product to open its official page (if available)

---

### Notes

- Replace the **RapidAPI Key** in `PriceAnalyticsService.java` with your own from [RapidAPI Dashboard](https://rapidapi.com/developer/dashboard).
- Some sources like eBay may not always return full product data.
- The backend is configured with CORS enabled for static frontend usage.
