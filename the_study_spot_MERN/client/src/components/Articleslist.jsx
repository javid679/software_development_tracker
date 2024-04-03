//client/src/components/Articleslist.jsx

import React, { useState, useEffect } from "react";
import axios from "axios";

function ArticleList() {
  const [articles, setArticles] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8081/api/article")
      .then((response) => {
        setArticles(response.data);
      })
      .catch((error) => {
        console.error("Error retrieving articles", error);
      });
  }, []);

  return (
    <div>
      <h2>Articles</h2>
      {articles.map((article) => (
        <div key={article._id}>
          <h3>{article.title}</h3>
          <p>{article.name}</p>
          <p>{article.category}</p>
          <p>{article.article}</p>
        </div>
      ))}
    </div>
  );
}

export default ArticleList;
