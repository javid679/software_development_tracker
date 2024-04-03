//D:\studyspot\server\index.js

require("dotenv").config();
const express = require("express");
const app = express();
const cors = require("cors");
const connection = require("./db");
const userRoutes = require("./routes/users");
const authRoutes = require("./routes/auth");
const artRoutes = require("./routes/article");
const Article = require("./models/article");

// database connection
connection();

// middlewares
app.use(express.json());
app.use(cors());

// routes
app.use("/api/article", artRoutes);
app.use("/api/users", userRoutes);
app.use("/api/auth", authRoutes);

app.get("/", (req, res) => {
  res.send("Hello, world!");
});
app.get("/api/article", async (req, res) => {
  try {
    const allArticles = await Article.find({});
    res.status(200).json(allArticles);
  } catch (error) {
    console.log(error);
    res.status(500).json({ error: "internal server error" });
  }
});
app.get("/api/article/:category", (req, res) => {
  const category = req.params.category;
  Article.find({ category: category }, function (err, articles) {
    if (err) {
      console.log(err);
      res.sendStatus(500);
    } else {
      const articleArray = articles.map((article) => {
        return {
          _id: article._id,
          title: article.title,
          name: article.name,
          category: article.category,

          description: article.description,
          article: article.article,
          email: article.email,
        };
      });
      res.send(articleArray);
    }
  });
});
app.get("/api/article/articleid/:id", (req, res) => {
  console.log("hello");
  console.log(req.params.id);
  const articleId = req.params.id;
  Article.findById(articleId, (err, article) => {
    if (err) {
      console.error("Error retrieving article", err);
      return res.status(500).send("Error retrieving article");
    }
    if (!article) {
      return res.status(404).send("Article not found");
    }
    res.send(article);
  });
});
app.delete("/api/article/delete/:id", async (req, res) => {
  try {
    const deletedArticle = await Article.findByIdAndDelete(req.params.id);
    res.status(200).json(deletedArticle);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
});
app.put("/api/article/update/:id", async (req, res) => {
  try {
    const article = await Article.findByIdAndUpdate(req.params.id, req.body, {
      new: true,
    });
    res.json(article);
  } catch (error) {
    console.error(error);
    res.status(500).send("Server Error");
  }
});

app.get("/api/article/email/:email", (req, res) => {
  const email = req.params.email;
  Article.find({ email: email }, function (err, articles) {
    if (err) {
      console.log(err);
      res.sendStatus(500);
    } else {
      const articleArray = articles.map((article) => {
        return {
          _id: article._id,
          title: article.title,
          name: article.name,
          category: article.category,

          description: article.description,
          article: article.article,
          email: article.email,
        };
      });
      res.send(articleArray);
    }
  });
});

const port = process.env.PORT || 8081;
app.listen(port, () => {
  console.log(`Server is running on http://localhost:${port}`);
});
