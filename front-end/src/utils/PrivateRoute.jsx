import { Outlet, Navigate } from "react-router-dom";

const PrivateRoutes = () => {
  var token = localStorage.getItem("token");
  return token != undefined ? <Outlet /> : <Navigate to="/signup" />;
};

export default PrivateRoutes;
