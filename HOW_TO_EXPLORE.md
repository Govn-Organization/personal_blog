# How to Explore This Project - Step by Step Guide

## 🎯 Start Here: Understanding the Basics

### Step 1: Run the Project First! (5 minutes)
**Why?** See it working before diving into code.

1. Open terminal in project folder
2. Run: `./mvnw spring-boot:run`
3. Open browser: `http://localhost:8080`
4. **Try these:**
   - Click "Create New Article"
   - Fill out the form and submit
   - Click on an article to view it
   - Go back to home

**What you just did:** You used the application! Now let's see how it works.

---

## 📚 Step 2: Understand the Data (Article Model) - 10 minutes

**File to read:** `src/main/java/.../model/Article.java`

**What to look for:**
- This is just a simple class that holds data
- It has 5 properties: `id`, `title`, `description`, `content`, `publishDate`
- Getters and Setters are just ways to read/write these properties

**Try this:**
1. Read the comments in the file
2. Understand: "This is like a box that holds article information"
3. Don't worry about the technical details yet - just understand it's a data container

**Key concept:** This is your "data structure" - it defines what an article looks like.

---

## 🔧 Step 3: Understand File Operations (Service) - 15 minutes

**File to read:** `src/main/java/.../service/ArticleService.java`

**What to look for:**
- This class reads and writes articles to files
- `getAllArticles()` - reads all JSON files from `articles/` folder
- `getArticleById(id)` - reads one specific article file
- `saveArticle(article)` - saves an article as a JSON file

**Follow the flow:**
1. Start with `getAllArticles()` method
2. Read the comments line by line
3. Understand: "It opens the articles folder, reads each JSON file, converts it to Article object"
4. Then look at `saveArticle()` - see how it does the opposite

**Try this:**
1. Create a JSON file manually in `articles/` folder (copy the example)
2. Restart the app
3. See your article appear on the home page!

**Key concept:** This is where the "work" happens - file reading/writing.

---

## 🎮 Step 4: Understand Web Requests (Controller) - 20 minutes

**File to read:** `src/main/java/.../controller/Personalblogcontroller.java`

**This is the most important part!** This connects URLs to actions.

**Follow each method:**

### Method 1: `home()` - Home Page
```
User visits: http://localhost:8080/
↓
Controller runs home() method
↓
Gets all articles from service
↓
Puts them in "model" (like a box)
↓
Returns "home" (shows home.html)
```

**Try this:**
1. Visit `http://localhost:8080/` in browser
2. Look at the `home()` method in controller
3. See how it matches!

### Method 2: `article(id)` - View One Article
```
User clicks article link
↓
URL becomes: /article/abc123
↓
Controller runs article(id) method with id="abc123"
↓
Gets that specific article
↓
Shows article.html
```

### Method 3: `newArticleForm()` - Show Form
```
User visits: /new
↓
Controller shows new.html (the form)
```

### Method 4: `createArticle()` - Save New Article
```
User fills form and clicks "Publish!"
↓
Form sends data to /new (POST request)
↓
Controller's createArticle() receives the data
↓
Creates Article object
↓
Saves it via service
↓
Redirects to home page
```

**Key concept:** Controller = traffic director. URL → Method → Action

---

## 🎨 Step 5: Understand HTML Templates - 15 minutes

**Files to read:**
1. `src/main/resources/templates/home.html`
2. `src/main/resources/templates/article.html`
3. `src/main/resources/templates/new.html`

**What to understand:**
- These are normal HTML files
- Thymeleaf adds special attributes to put Java data in HTML
- `th:text="${article.title}"` = "Put article.title here"
- `th:each="article : ${articles}"` = "Repeat for each article"

**Follow the flow in home.html:**
1. Controller sends `articles` list to template
2. Template uses `th:each` to loop through articles
3. For each article, shows title and date
4. Creates links using `th:href`

**Try this:**
1. Open `home.html`
2. Find where it displays articles
3. See how `th:each` loops through them
4. Understand how `th:href` creates the links

**Key concept:** Templates = HTML + Java data mixed together

---

## 🔄 Step 6: Follow a Complete Flow - 20 minutes

**Scenario:** User creates a new article

### Step-by-step flow:

1. **User clicks "Create New Article" link**
   - Link is in `home.html`: `<a th:href="@{/new}">`
   - Browser goes to: `http://localhost:8080/new`

2. **Controller receives request**
   - `@GetMapping("/new")` matches the URL
   - Runs `newArticleForm()` method
   - Returns `"new"` → shows `new.html`

3. **User fills form and clicks "Publish!"**
   - Form has `th:action="@{/new}"` and `method="post"`
   - Browser sends POST request to `/new`

4. **Controller receives POST request**
   - `@PostMapping("/new")` matches
   - Runs `createArticle()` method
   - Gets form data: `title`, `description`, `content`, `publishDate`

5. **Controller creates Article object**
   ```java
   Article article = new Article();
   article.setTitle(title);  // from form
   article.setDescription(description);  // from form
   // etc...
   ```

6. **Controller saves article**
   - Calls `articleService.saveArticle(article)`
   - Service saves it as JSON file

7. **Controller redirects**
   - `return "redirect:/";` → goes back to home page
   - Home page now shows the new article!

**Try this:**
1. Add a `System.out.println()` in `createArticle()` method
2. Create an article
3. See the print in console - you'll see the data flow!

---

## 🧪 Step 7: Experiment and Break Things! - 30 minutes

**Best way to learn:** Try changing things and see what happens!

### Experiments to try:

1. **Change the home page**
   - Edit `home.html` - add some text
   - Refresh browser - see your change!

2. **Add a field to Article**
   - Add `private String author;` to Article.java
   - Add getter/setter
   - Add input field in `new.html`
   - See if you can save and display it!

3. **Change the service**
   - In `getAllArticles()`, try sorting differently
   - Or filter out certain articles

4. **Add a new page**
   - Create a new method in controller: `@GetMapping("/about")`
   - Create `about.html` template
   - Visit `/about` in browser

5. **Add print statements**
   - Add `System.out.println("Got here!");` in methods
   - See when they run

---

## 📖 Step 8: Learn the Concepts - As You Go

**Don't try to learn everything at once!** Learn concepts as you need them:

### When you see `@GetMapping`:
- **What it means:** "When someone visits this URL with GET request, run this method"
- **Example:** `@GetMapping("/")` = home page

### When you see `@PostMapping`:
- **What it means:** "When form is submitted (POST request), run this method"
- **Example:** `@PostMapping("/new")` = handle form submission

### When you see `@Autowired`:
- **What it means:** "Spring, please give me this object automatically"
- **Example:** `@Autowired ArticleService` = Spring creates and gives us the service

### When you see `Model model`:
- **What it means:** "A box to put data in, to send to HTML template"
- **Example:** `model.addAttribute("articles", ...)` = put articles in the box

### When you see `th:something`:
- **What it means:** "Thymeleaf - put Java data here"
- **Example:** `th:text="${article.title}"` = display article.title

---

## 🎯 Recommended Learning Path

**Day 1:**
- Run the project ✅
- Understand Article.java ✅
- Understand one controller method (home) ✅

**Day 2:**
- Understand ArticleService ✅
- Understand how forms work (new article) ✅
- Follow one complete flow ✅

**Day 3:**
- Experiment with HTML templates ✅
- Try adding a field ✅
- Try creating a new page ✅

**Day 4+:**
- Experiment more!
- Try breaking things and fixing them
- Read Spring Boot documentation for concepts you don't understand

---

## 💡 Pro Tips

1. **Use the debugger!** Set breakpoints and step through code
2. **Add print statements** to see what's happening
3. **Read error messages** - they tell you what's wrong
4. **One thing at a time** - don't try to understand everything at once
5. **Google is your friend** - search "Spring Boot @GetMapping" if confused

---

## ❓ Common Questions

**Q: Why do we need a Service?**
A: To separate concerns. Controller handles web stuff, Service handles business logic (file operations).

**Q: What's the difference between GET and POST?**
A: GET = viewing a page, POST = submitting data (like a form)

**Q: Why Thymeleaf?**
A: It lets us put Java data into HTML easily. There are other options, but this is simple.

**Q: Where does the data actually go?**
A: JSON files in the `articles/` folder. Check it out after creating an article!

---

## 🚀 Next Steps After Understanding This

Once you understand this project, you can learn:
- Databases (instead of files)
- Authentication (login system)
- REST APIs
- More advanced Spring Boot features

But first, master this simple version!






