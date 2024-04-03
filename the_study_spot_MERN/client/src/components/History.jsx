//D:\studyspot\client\src\components\History.jsx
import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate, Routes, Route } from "react-router-dom";
import Article from "./Article";
import { Link } from "react-router-dom";
import "./styles.css";

function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate("/login");
  };

  const handleClick = () => {
    navigate("/Publish");
  };
  const handleCategoryClick = (category) => {
    navigate(`/category/${category}`);
  };

  return (
    <nav className="navbar">
      <ul>
        <li>
          <Link to="/display">
            <button className="navbuton">
              <b>All articles</b>
            </button>
          </Link>
        </li>
        <li>
          <Link to="/Technology">
            <button className="navbuton">
              <b>Technology</b>
            </button>
          </Link>
        </li>
        <li>
          <Link to="/research">
            <button className="navbuton">
              <b>research</b>
            </button>
          </Link>
        </li>
        <li>
          <Link to="/Programming-languages">
            <button className="navbuton">
              <b>Programming-languages</b>
            </button>
          </Link>
        </li>
        <li>
          <Link to="/gaming">
            <button className="navbuton">
              <b>Gaming</b>
            </button>
          </Link>
        </li>
        <li>
          <Link to="/sports">
            <button className="navbuton">
              <b>sports</b>
            </button>
          </Link>
        </li>
        <li>
          <Link to="/social-media">
            <button className="navbuton">
              <b>Social-media</b>
            </button>
          </Link>
        </li>
        <li>
          <Link to="/history">
            <button className="navbuton">
              <b>History</b>
            </button>
          </Link>
        </li>
        <li>
          <Link to="/HomePage">
            <button className="navbuton">
              <b>back to home</b>
            </button>
          </Link>
        </li>

        <div>
          <button className="publish" onClick={handleClick}>
            Publish article!
          </button>
        </div>
      </ul>
    </nav>
  );
}
function ArticlesList() {
  const [articles, setArticles] = useState([]);
  const navigate = useNavigate();

  const handleRead = (articleId) => {
    navigate("/Article");
  };

  useEffect(() => {
    axios
      .get("http://localhost:8081/api/article/History")
      .then((response) => {
        if (Array.isArray(response.data)) {
          setArticles(response.data);
        } else {
          console.error("Response data is not an array", response.data);
        }
      })
      .catch((error) => {
        console.error("Error retrieving articles", error);
      });
  }, []);

  return (
    <>
      <Navbar />
      {articles.length > 0 ? (
        <div className="article-grid">
          {articles.map((article) => (
            <div key={article._id} className="article-block">
              <h3 className="articletitle">Title: {article.title}</h3>
              <p>
                <i>Published by: {article.name}</i>
              </p>
              <p>
                <i>category: {article.category}</i>
              </p>
              <p>
                <i>Description: {article.description}</i>
              </p>

              <button
                onClick={() => handleRead(article._id)}
                className="readmore"
              >
                Read more
              </button>
            </div>
          ))}
        </div>
      ) : (
        <p className="noarticles">No articles</p>
      )}
    </>
  );
}

function History() {
  return (
    <>
      <Routes>
        <Route path="/" element={<ArticlesList />} />
        <Route path="/Article/:articleId" element={<Article />} />
      </Routes>
    </>
  );
}

export default History;
