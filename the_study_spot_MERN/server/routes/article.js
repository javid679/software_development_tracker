//D:\studyspot\server\routes\article.js

const express = require("express");
const router = express.Router();
const Article = require("../models/article");

router.post("/", (req, res) => {
  const articleData = req.body;
  const article = new Article(articleData);
  article.save((err, savedArticle) => {
    if (err) {
      console.error("Error saving article", err);
      res.status(500).send();
    } else {
      console.log("Article saved successfully", savedArticle);
      res.send(savedArticle);
    }
  });
});
router.get("/api/article", async (req, res) => {
  res.send("this is articles");
});

module.exports = router;
