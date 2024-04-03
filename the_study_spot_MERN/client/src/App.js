//D:\studyspot\client\src\App.js

import { Route, Routes, Navigate } from "react-router-dom";
import Signup from "./components/Singup";
import Login from "./components/Login";
import "./stylesss.css";
import Display from "./components/Display";
import Publish from "./components/Publish";
import Article from "./components/Article";
import Technology from "./components/Technology";
import Latest from "./components/Latest";
import Sports from "./components/sports";
import Research from "./components/research";
import Programming_languages from "./components/Programming-languages";
import Gaming from "./components/gaming";
import Social_media from "./components/social-media";
import History from "./components/History";
import About from "./components/About";
import EditArticle from "./components/editArticle";

import HomePage from "./components/HomePage";

function App() {
  const user = localStorage.getItem("token");

  return (
    <>
      <header>
        <div className="headcomp">
          <h1 className="headertag">The StudySpot</h1>
          <p className="tagline">
            <i>
              <span>
                "Unlock Your Learning Potential: Explore Articles on Study Spot"
              </span>
            </i>
          </p>
        </div>
      </header>

      <Routes>
        {user && <Route path="/display" exact element={<Display />} />}
        <Route path="/signup" exact element={<Signup />} />
        <Route path="/login" exact element={<Login />} />
        <Route path="/" element={<Navigate replace to="/login" />} />
        <Route path="/Publish" exact element={<Publish />} />
        <Route path="/HomePage" exact element={<HomePage />} />
        <Route path="/Article" exact element={<Article />} />
        <Route path="/Technology" exact element={<Technology />} />
        <Route path="/latest" exact element={<Latest />} />
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
        <Route path="/About" exact element={<About />} />
        <Route path="/edit/:id" exact element={<EditArticle />} />
      </Routes>
    </>
  );
}

export default App;
