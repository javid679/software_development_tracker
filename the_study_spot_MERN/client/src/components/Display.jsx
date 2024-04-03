//D:\studyspot\client\src\components\Display.jsx

import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import "./styles.css";
import Contact from "./Contact";
import About from "./About";
import Home from "./Home";
import Latest from "./Latest";
import { useNavigate } from "react-router-dom";
import Publish from "./Publish";
import { useState, useEffect } from "react";
import axios from "axios";
import Article from "./Article";
import Technology from "./Technology";
import Sports from "./sports";
import Research from "./research";
import Programming_languages from "./Programming-languages";
import Gaming from "./gaming";
import Social_media from "./social-media";
import History from "./History";

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
    localStorage.setItem("articleId", articleId);
    console.log(articleId);
    navigate("/Article");
  };

  useEffect(() => {
    axios
      .get("http://localhost:8081/api/article")
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
    <div className="article-grid">
      {articles.map((article) => (
        <div key={article._id} className="article-block">
          <h3 className="articletitle">Title: {article.title}</h3>
          <p>
            <i>Published by: {article.name}</i>
          </p>
          <p>
            <i>category:{article.category}</i>
          </p>
          <p>
            <i>Description: {article.description}</i>
          </p>

          <button onClick={() => handleRead(article._id)} className="readmore">
            Read more
          </button>
        </div>
      ))}
    </div>
  );
}

function Display() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/Publish" exact element={<Publish />} />
        <Route path="/Article" element={<Article />} />
        <Route path="/Technology" exact element={<Technology />} />
        <Route path="/sports" exact element={<Sports />} />
        <Route path="/research" exact element={<Research />} />
        <Route
          path="/Programming-languages"
          exact
          element={<Programming_languages />}
        />
        <Route path="/gaming" exact element={<Gaming />} />
        <Route path="/social-media" exact element={<Social_media />} />
        <Route path="/history" exact element={<History />} />
      </Routes>

      <div className="display">
        <Navbar />
        <div className="content">
          <div className="sidebar-and-articles-container">
            <ArticlesList />
          </div>
        </div>
      </div>
    </>
  );
}
export default Display;
