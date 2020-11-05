import React from "react";
import { Navbar, NavItem } from "react-bootstrap";
import { Route, BrowserRouter, Link } from "react-router-dom";
import Dashboard from "../components/Dashboard";
import MyComponent from "../components/MyComponent";

const MyRouter = () => {
  return (
    <BrowserRouter>
      <Navbar bg="dark" className="sticky-top">
        <Navbar.Brand>
          <Link to="/main">Main</Link>
        </Navbar.Brand>
        <Navbar.Brand>
          <Link to="/dashboard">Dashboard</Link>
        </Navbar.Brand>
      </Navbar>
      <Route path="/main" exact component={MyComponent} />
      <Route path="/dashboard" exact component={Dashboard} />
    </BrowserRouter>
  );
};

export default MyRouter;
