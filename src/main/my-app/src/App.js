import logo from './logo.svg';
import './App.css';
import axios from "axios";
import {useDropzone} from 'react-dropzone'
import React, {useState, useEffect, useCallback} from "react";

const Users = () => {
  const [users, setUsers] = useState([]);

const fetchUsers = () => {

  axios.get("http://localhost:8080/api/user").then(res => {
    console.log(res);
    setUsers(res.data);
  });
};

useEffect(() => {
  fetchUsers();
}, []);

return users.map((user, index) => {
  return (
  <div key={index}>
    {/* TODO: put image here */}
    {user.userId ? (<img src={`http://localhost:8080/api/user/${user.userId}/image/download`}/>) : null}
    <br/>
    <br/>
    <h1>{user.userName}</h1>
    <p>{user.userId}</p>
    <Dropzone {...user}/>
    <br/>
  </div>
  )
});

};

function Dropzone({ userId }) {
  const onDrop = useCallback(acceptedFiles => {
    // Do something with the files
    const file = acceptedFiles[0];
    console.log(file);
    const formData = new FormData();
    formData.append("file", file);

    axios.post(`http://localhost:8080/api/user/${userId}/image/upload`, formData, 
    {
      headers: {
        "Content-Type": "multipart/form-data"
      }
    }).then(() => {
      console.log("file uploaded successfully")
    }).catch(err => {
      console.log(err);
    });
  }, []);
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the image here ...</p> :
          <p className="imageUploader">Click here to upload user image.</p>
      }
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <Users/>
    </div>
  );
}

export default App;
