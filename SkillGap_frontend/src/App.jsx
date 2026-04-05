import React from "react";
import Login from "./components/Login";
import Register from "./components/Register";
import Upload from "./components/Upload";

function App() {
  return (
    <div>
      <h1>Resume Analyzer</h1>

      <Register />
      <hr />

      <Login />
      <hr />

      <Upload />
    </div>
  );
}

export default App;