//D:\studyspot\client\src\components\Latest.jsx

import React, { useState, useEffect } from "react";
import axios from "axios";
import "./styles.css";
import EditArticle from "./editArticle";
import { Routes, Route, Link } from "react-router-dom";

function Latest() {
  const [articles, setArticles] = useState([]);

  const email = localStorage.getItem("email");

  useEffect(() => {
    axios
      .get(`http://localhost:8081/api/article/email/${email}`)
      .then((response) => {
        setArticles(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  const deleteArticle = (id) => {
    axios
      .delete(`http://localhost:8081/api/article/delete/${id}`)
      .then(() => {
        const updatedArticles = articles.filter(
          (article) => article._id !== id
        );
        setArticles(updatedArticles);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
    <>
      <div>
        <div>
          <h1 className="latestheading">My Articles</h1>
        </div>
        {articles.length === 0 ? (
          <p>No articles published by you.</p>
        ) : (
          <div className="grid-container">
            {articles.map((article) => (
              <div key={article._id} className="grid-item">
                <h2 className="latestheading">{article.title}</h2>
                <p>
                  <strong>Name:</strong> {article.name}
                </p>
                <p>
                  <strong>Category:</strong> {article.category}
                </p>
                <p>
                  <strong>Description:</strong> {article.description}
                </p>
                <div className="actions">
                  <button
                    className="delete"
                    onClick={() => deleteArticle(article._id)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}
      </div>
      <Routes>
        <Route path="/edit:id" exact element={<EditArticle />} />
      </Routes>
    </>
  );
}

export default Latest;
