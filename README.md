# Personal Blog - Simple Learning Project

This is a simple blog project to learn Spring Boot. Everything is explained with comments in the code!

## How It Works (Simple Explanation)

### 1. **Article.java** (Model)
- This is just a container for article data
- Like a box that holds: title, description, content, date, and ID
- Has getters/setters to read and write the data

### 2. **ArticleService.java** (Service)
- Does the "work" - reads and writes articles to files
- Saves articles as JSON files in the `articles/` folder
- When you ask for articles, it reads all JSON files and returns them

### 3. **Personalblogcontroller.java** (Controller)
- Handles web requests (when someone visits a URL)
- Like a traffic director:
  - Visit `/` → shows home page with list of articles
  - Visit `/article/123` → shows article with ID 123
  - Visit `/new` → shows form to create new article
  - Submit form → saves article and goes back to home

### 4. **HTML Templates** (Frontend)
- `home.html` - shows list of all articles
- `article.html` - shows one article
- `new.html` - form to create new article
- Uses Thymeleaf to put Java data into HTML

## How Data Flows

1. **User visits home page** (`/`)
   - Controller calls `articleService.getAllArticles()`
   - Service reads all JSON files from `articles/` folder
   - Controller puts articles in the "model" (like a box)
   - Thymeleaf takes articles from model and puts them in HTML
   - User sees the list of articles

2. **User creates new article**
   - User fills out form on `/new` page
   - Clicks "Publish!" button
   - Form sends data to controller's `createArticle()` method
   - Controller creates Article object and fills it with form data
   - Controller calls `articleService.saveArticle()`
   - Service saves article as JSON file in `articles/` folder
   - Controller redirects to home page
   - New article appears in the list!

## File Structure

```
articles/              ← Articles are stored here as JSON files
src/
  main/
    java/
      controller/      ← Handles web requests
      model/          ← Article class (data container)
      service/        ← Reads/writes articles to files
    resources/
      templates/      ← HTML pages
      static/css/     ← CSS styling
```

## How to Run

1. Make sure you have Java 21 installed
2. Run: `./mvnw spring-boot:run`
3. Open browser: `http://localhost:8080`

## Key Spring Boot Concepts Used

- **@Controller** - Tells Spring this class handles web pages
- **@Service** - Tells Spring this class does business logic
- **@GetMapping** - Handles GET requests (viewing pages)
- **@PostMapping** - Handles POST requests (form submissions)
- **@Autowired** - Spring automatically gives us the service
- **Model** - Container to send data from controller to HTML
- **Thymeleaf** - Template engine to put Java data in HTML

## That's It!

This is a simple project - no database, no authentication, just basic CRUD (Create, Read) operations with files. Perfect for learning!
