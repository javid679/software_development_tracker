//D:\studyspot\client\src\components\Profile.jsx

import React from "react";
import "./styles.css";

function Profile() {
  return (
    <div className="aboutcontent">
      <header>
        <h1>Profile_(user_name)</h1>
        <nav>
          <ul>
            <li>
              <a href="#">Home</a>
            </li>
            <li>
              <a href="#">About</a>
            </li>
            <li>
              <a href="#">Uploads</a>
            </li>
            <li>
              <a href="#">Messages</a>
            </li>
          </ul>
        </nav>
      </header>
      <main>
        <section className="profile">
          <div className="cover-photo">
            <img src="wallpapers.jfif" alt="Cover Photo" />
            <button className="btn-cover-photo">Change Cover Photo</button>
          </div>
          <div className="profile-info">
            <div className="profile-pic">
              <img src="wallpapers.jfif" alt="Profile Picture" />
              <button className="btn-profile-pic">Change Profile Photo</button>
            </div>
            <div className="bio">
              <h2>About Me</h2>
              <p>
                I am a web developer with 5 years of experience in front-end
                development. I specialize in HTML, CSS, and JavaScript, and I am
                always looking for new challenges and opportunities to learn and
                grow.
              </p>
            </div>
          </div>
        </section>
        <section className="work-experience">
          <h2>Work Experience</h2>
          <ul>
            <li>
              <h3>Web Developer, Company A</h3>
              <p>
                Developed and maintained multiple websites using HTML, CSS, and
                JavaScript.
              </p>
            </li>
            <li>
              <h3>Front-End Developer, Company B</h3>
              <p>
                Collaborated with design and development teams to build
                responsive web applications using React and Redux.
              </p>
            </li>
          </ul>
        </section>
      </main>
      <footer>
        <p>&copy; 2023 My Portfolio</p>
      </footer>
    </div>
  );
}

export default Profile;
