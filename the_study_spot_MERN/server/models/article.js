//server/models/articles.js

const mongoose = require("mongoose");

const articleSchema = new mongoose.Schema({
  title: {
    type: String,
    required: true,
  },
  name: {
    type: String,
    required: true,
  },
  category: {
    type: String,
    required: true,
  },
  description: {
    type: String,
    required: true,
  },
  article: {
    type: String,
    required: true,
  },
  email: {
    type: String,
    required: true,
  },
});

const Article = mongoose.model("article", articleSchema);

module.exports = Article;
