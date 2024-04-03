//D:\studyspot\client\src\components\Publish.jsx

import React, { useState } from "react";
import "./styles.css";
import axios from "axios";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Publish() {
  const [title, setTitle] = useState("");
  const [name, setName] = useState("");
  const [category, setCategory] = useState("");
  const [description, setDescription] = useState("");

  const [article, setArticle] = useState("");
  const [email, setEmail] = useState("");
  const username = localStorage.getItem("username");
  const navigate = useNavigate();

  useEffect(() => {
    const email = localStorage.getItem("email");
    setEmail(email);
  }, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    const articleData = {
      title: title,
      name: username,
      category: category,
      description: description,
      article: article,
      email: email,
    };
    axios
      .post("http://localhost:8081/api/article", articleData)
      .then((response) => {
        console.log("article saved successfully", response.data);
        window.alert("article published successfully");

        setTitle("");
        setName(username);
        setCategory("");
        setDescription("");
        setArticle("");
        setEmail(email);
        navigate("/display");
      })
      .catch((error) => {
        console.error("error saving article", error);
      });
    console.log("Article submitted:", {
      title,
      name,
      category,
      description,
      article,
    });
  };

  return (
    <div className="publish-article-container">
      <h1 className="title">Publish an Article</h1>
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label htmlFor="title" className="label">
            Title:
          </label>
          <input
            type="text"
            id="title"
            className="input"
            value={title}
            onChange={(event) => setTitle(event.target.value)}
          />
        </div>
        <div className="form-group">
          <label htmlFor="name" className="label">
            Name:
          </label>
          <input type="text" id="name" className="input" value={username} />
        </div>
        <div className="form-group">
          <label htmlFor="category" className="label">
            Category:
          </label>
          <div className="radio-group">
            <label htmlFor="technology" className="radio-label">
              <input
                type="radio"
                id="technology"
                name="category"
                value="technology"
                checked={category === "technology"}
                onChange={(event) => setCategory(event.target.value)}
              />
              Technology
            </label>
            <label htmlFor="sports" className="radio-label">
              <input
                type="radio"
                id="sports"
                name="category"
                value="sports"
                checked={category === "sports"}
                onChange={(event) => setCategory(event.target.value)}
              />
              Sports
            </label>
            <label htmlFor="social-media" className="radio-label">
              <input
                type="radio"
                id="social-media"
                name="category"
                value="social-media"
                checked={category === "social-media"}
                onChange={(event) => setCategory(event.target.value)}
              />
              Social Media
            </label>
            <label htmlFor="research" className="radio-label">
              <input
                type="radio"
                id="research"
                name="category"
                value="research"
                checked={category === "research"}
                onChange={(event) => setCategory(event.target.value)}
              />
              Research
            </label>
            <label htmlFor="History" className="radio-label">
              <input
                type="radio"
                id="History"
                name="category"
                value="History"
                checked={category === "History"}
                onChange={(event) => setCategory(event.target.value)}
              />
              History
            </label>
            <label htmlFor="Programming-languages" className="radio-label">
              <input
                type="radio"
                id="Programming-languages"
                name="category"
                value="Programming-languages"
                checked={category === "Programming-languages"}
                onChange={(event) => setCategory(event.target.value)}
              />
              Programming-languages
            </label>
            <label htmlFor="gaming" className="radio-label">
              <input
                type="radio"
                id="gaming"
                name="category"
                value="gaming"
                checked={category === "gaming"}
                onChange={(event) => setCategory(event.target.value)}
              />
              gaming
            </label>
            <label htmlFor="other" className="radio-label">
              <input
                type="radio"
                id="other"
                name="category"
                value="Programming-languages"
                checked={category === "other"}
                onChange={(event) => setCategory(event.target.value)}
              />
              other
            </label>

            {/* Add more radio buttons for other categories here */}
          </div>
        </div>
        <div className="form-group">
          <label htmlFor="email" className="label">
            Title:
          </label>
          <input type="email" id="title" className="input" value={email} />
        </div>
        <div className="form-group">
          <label htmlFor="description" className="label">
            Description:
          </label>
          <textarea
            id="description"
            className="textarea"
            value={description}
            onChange={(event) => setDescription(event.target.value)}
          />
        </div>

        <div className="form-group">
          <label htmlFor="article" className="label">
            Article:
          </label>
          <textarea
            id="article"
            className="textarea"
            value={article}
            onChange={(event) => setArticle(event.target.value)}
          />
        </div>

        <button type="submit" className="button">
          Publish
        </button>
      </form>
    </div>
  );
}

export default Publish;
