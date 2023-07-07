// eslint-disable-next-line no-unused-vars
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Landing from "./Pages/Landing";
import SignUp from "./Pages/Signup";
import Details from "./Pages/Details";
import Cart from "./Pages/Cart";
import Checkout from "./Pages/Checkout";
import Signin from "./Pages/Signin";
import Profile from "./Pages/Profile";
import Ordertracking from "./Pages/OrderTracking";
import Contact from "./Pages/Contact";
import OrderSum from "./Pages/OrderSummary";
import Wishlist from "./Pages/Wishlist";
import MyOrder from "./Pages/MyOrder";
import Product from "./Pages/ProductsPage";
import PrivacyPolicy from "./Pages/PrivacyPolicyPage";
import TermsConditions from "./Pages/TermsPage";
import RefundPolicy from "./Pages/RefundPolicy";
import FaqPage from "./Pages/FaqPage";
import About from "./Pages/About";
import ShippingPolicy from "./Pages/ShippingPolicy";
import PrivateRoutes from "./utils/PrivateRoute";

function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route exact path="/" element={<Landing home={false} />}></Route>
          <Route exact path="/signin" element={<Signin />}></Route>
          <Route exact path="/signup" element={<SignUp />}></Route>
          <Route exact path="/products" element={<Product />}></Route>
          <Route exact path="/detials" element={<Details />}></Route>

          {/* ----------- Private Route ------------------------------- */}
          <Route element={<PrivateRoutes />}>
            <Route path="/home" element={<Landing home={true} />} />
            <Route path="/cart" element={<Cart />}></Route>
            <Route path="/checkout" element={<Checkout />}></Route>
            <Route exact path="/profile" element={<Profile />}></Route>
            <Route exact path="/tracking" element={<Ordertracking />}></Route>
            <Route exact path="/summary" element={<OrderSum />}></Route>
            <Route exact path="/wishlist" element={<Wishlist />}></Route>
            <Route exact path="/myorder" element={<MyOrder />}></Route>
          </Route>

          <Route exact path="/contact" element={<Contact />}></Route>
          <Route exact path="/policy" element={<PrivacyPolicy />}></Route>
          <Route exact path="/terms" element={<TermsConditions />}></Route>
          <Route exact path="/refundpolicy" element={<RefundPolicy />}></Route>
          <Route exact path="/faq" element={<FaqPage />}></Route>
          <Route exact path="/about" element={<About />}></Route>
          <Route exact path="/shippolicy" element={<ShippingPolicy />}></Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
