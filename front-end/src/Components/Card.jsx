// eslint-disable-next-line no-unused-vars
import React, { useState, useEffect } from "react";
import img1 from "../assets/frock.png";
import img2 from "../assets/star.png";
import img3 from "../assets/star5.png";
import img5 from "../assets/p3.png";
import img6 from "../assets/p2.png";
import FavoriteBorderOutlinedIcon from "@mui/icons-material/FavoriteBorderOutlined";
import FavoriteOutlinedIcon from "@mui/icons-material/FavoriteOutlined";
import img7 from "../assets/p1.png";
import Rating from "@mui/material/Rating";
import { useNavigate } from "react-router-dom";
import { useAlert } from "react-alert";
import axios from "../axiosConfig";

export default function Cards() {
  // let navigate = useNavigate();
  // const alert = useAlert();

  // const [value, setValue] = useState(4.5);

  // const [show1, setShow1] = useState(true);
  // const [show2, setShow2] = useState(true);
  // const [show3, setShow3] = useState(true);
  // const [show4, setShow4] = useState(true);

  const [data, setData] = useState([]);
  // const [error, setError] = useState(null);

  const getProduct = async () => {
    try {
      const response = await axios.get("products/0/8");
      console.log(response.data);
      setData(response.data.slice(0, 4));

      if (response.data.errormessage) {
        // setErrorMessage(response.data.errormessage);
        // setTimeout(() => setErrorMessage(""), 3000);
        console.log(response.data.errormessage);
        return;
      }
    } catch (error) {
      console.log(error);
    }
  };
  useEffect(() => {
    getProduct();
  });

  return (
    <div>
      <div className="bg-white py-6 sm:py-8 lg:py-12">
        <div className="mx-auto max-w-screen-2xl px-4 md:px-8">
          <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
            {/* <!-- product - start --> */}
            {/* <CardItem productName={data[0].productName} /> */}
            {data.map((i) => {
              console.log(i.productName);
              // eslint-disable-next-line react/jsx-key
              return <CardItem parms={i} key={i.productId} />;
            })}

            {/* <CardItem /> */}
            {/* <!-- product - end --> */}
            {/* <CardItem productName={data[0].productName} />; */}
          </div>
        </div>
      </div>
    </div>
  );
}

function CardItem(props) {
  console.log(props.parms.productPics)
  let navigate = useNavigate();
  const alert = useAlert();
  let token = localStorage.getItem("token");

  const addToCart = async (parms) => {
    try {
      const response = await axios.post(
        "/user/cart",
        {
          productId: parms.productId,
          size: parms.size,
          quantity: 1,
          color: parms.colors,
        },
        {
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${token}`,
          },
        }
      );
      console.log(response.data);

      if (response.data.errormessage) {
        // setErrorMessage(response.data.errormessage);
        // setTimeout(() => setErrorMessage(""), 3000);
        console.log(response.data.errormessage);
        return;
      }
    } catch (error) {
      console.log(error);
    }
  };

  const [show1, setShow1] = useState(true);
  return (
    <div>
      <a
        href="/detials"
        className="group relative block h-96 overflow-hidden rounded-t-lg bg-gray-100"
      >
        <img
          src="https://images.unsplash.com/photo-1552374196-1ab2a1c593e8?auto=format&q=75&fit=crop&crop=top&w=600&h=700"
          loading="lazy"
          alt="Photo by Austin Wade"
          className="h-full w-full object-cover object-center transition duration-200 group-hover:scale-110"
        />
      </a>

      <div className="flex items-start justify-between gap-2 rounded-b-lg bg-gray-100 p-4">
        <div className="flex flex-col">
          <a
            href="/detials"
            className="font-bold text-gray-800 transition duration-100 hover:text-gray-500 lg:text-lg"
          >
            {props.parms.productName}
          </a>

          <div className="mt-1 flex items-center">
            <Rating
              name="read-only"
              value={props.parms.rating}
              readOnly
              size="small"
              style={{ color: "black" }}
              precision={0.5}
            />
          </div>
        </div>

        <div className="flex flex-col items-end mt-1">
          <span className="font-bold text-gray-600 lg:text-lg">
            ${props.parms.productPrice}
          </span>
          <div className="flex items-center mt-1">
            <button
              onClick={() => {
                setShow1(!show1);
                let stro = show1 ? "added to" : "removed from";
                alert.success(`${props.parms.productName} ${stro} watchlist!`);
              }}
              className="mb-0.5"
            >
              {show1 ? (
                <FavoriteBorderOutlinedIcon style={{ fontSize: "24px" }} />
              ) : (
                <FavoriteOutlinedIcon
                  style={{ color: "#FE5455", fontSize: "24px" }}
                />
              )}
            </button>
            <button
              className=" ml-1 rounded text-sm font-md hover:text-white hover:bg-black text-black border  border-slate-700 px-1 py-1"
              onClick={() => {
                alert.success(`${props.parms.productName} added to cart!`);
                console.log(props.parms);
                addToCart(props.parms);
              }}
            >
              Add To Cart{" "}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
