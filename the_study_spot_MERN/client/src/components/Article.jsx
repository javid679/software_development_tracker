//D:\studyspot\client\src\components\Article.jsx
import { useState, useEffect } from "react";
import axios from "axios";
import "./styles.css";

function Article() {
  const [article, setArticle] = useState({});
  const articleId = localStorage.getItem("articleId");

  useEffect(() => {
    axios
      .get("http://localhost:8081/api/article/articleid/" + articleId)
      .then((response) => {
        setArticle(response.data);
      })
      .catch((error) => {
        console.error("Error retrieving article", error);
      });
  }, [articleId]);

  return (
    <div className="article-content">
      <h2>{article.title}</h2>
      <p>
        <i>published by:{article.name}</i>
      </p>
      <p>
        <i>description:{article.description}</i>
      </p>
      <p>{article.article}</p>
    </div>
  );
}

export default Article;
