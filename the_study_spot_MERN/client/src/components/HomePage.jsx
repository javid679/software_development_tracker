//D:\studyspot\client\src\components\HomePage.jsx
import React from "react";
import { useNavigate } from "react-router-dom";
import "./styles.css";
import { Routes, Route, Link } from "react-router-dom";
import { useState } from "react";
import { useEffect } from "react";
import About from "./About";
import Latest from "./Latest";

function HomePage() {
  const navigate = useNavigate();
  const email = localStorage.getItem("email");

  const handleClick = () => {
    navigate("/display");
  };
  const [userId, setUserId] = useState(null);
  const [username, setUsername] = useState(null);

  useEffect(() => {
    const userId = localStorage.getItem("userid");
    setUserId(userId);
  }, []);
  useEffect(() => {
    const username = localStorage.getItem("username");
    setUsername(username);
  }, []);

  return (
    <>
      <Navbar />
      <div
        className="home-page"
        style={{
          backgroundImage: `url(${process.env.PUBLIC_URL}/projbg.jpg)`,
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
        }}
      >
        <div className="home-page-content">
          <h1 className="username">Hello {username}!</h1>
          <h1 className="title">Welcome to our website!</h1>

          <p className="description">
            <i>
              {" "}
              Where knowledge meets community. Join our platform to discover and
              share insightful articles, connect with fellow learners, and
              enrich your learning journey.{" "}
            </i>
          </p>
          <button onClick={handleClick} className="all-articles-button">
            Explore Articles
          </button>
        </div>
      </div>
    </>
  );
}

function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate("/login");
  };

  return (
    <>
      <nav className="navbar">
        <ul>
          <li>
            <Link to="/HomePage">
              <button className="navbuton">
                <b>Home</b>
              </button>
            </Link>
          </li>
          <li>
            <Link to="/About">
              <button className="navbuton">
                <b>About us</b>
              </button>
            </Link>
          </li>
          <li>
            <Link to="/latest">
              <button className="navbuton">
                <b>My articles</b>
              </button>
            </Link>
          </li>
          <li>
            <button className="navbuton" onClick={handleLogout}>
              <b>Logout</b>
            </button>
          </li>
        </ul>
      </nav>
      <Routes>
        <Route path="/About" exact element={<About />} />
        <Route path="/latest" exact element={<Latest />} />
      </Routes>
    </>
  );
}

export default HomePage;
