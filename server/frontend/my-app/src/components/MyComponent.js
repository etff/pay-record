import React from "react";
import axios from "axios";

const getApi = () => {
  axios
    .get("http://localhost:8080/api/hello")
    .then((res) => {
      console.log("response ====>",res);

    })
    .catch((res) => console.log(res));
};

const MainComponent = () => {
  getApi();
  return <div>Main 페이지</div>;
};

export default MainComponent;
