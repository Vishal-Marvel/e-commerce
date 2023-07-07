// eslint-disable-next-line no-unused-vars
import React from "react";
import { ReactSearchAutocomplete } from "react-search-autocomplete";
import { useNavigate } from "react-router-dom";

export default function Search() {
  let navigate = useNavigate();

  const items = [
    {
      id: 0,
      name: "WHITE DOTTED FROCK",
    },
    {
      id: 1,
      name: "BOULT AUDIOJACK 4.98",
    },
    {
      id: 2,
      name: "TRENDY BLACK TOP",
    },
    {
      id: 3,
      name: "ZERO 2.1 HOME THEATER",
    },
    {
      id: 4,
      name: "Yoyo",
    },
  ];

  const handleOnSelect = (item) => {
    // navigate("/products", { state: { searchItem: item }, replace: true });
    console.log(item);
  };

  const handleOnFocus = () => {
    console.log("Focused");
  };

  const handleOnClear = () => {
    console.log("Cleared");
  };

  const formatResult = (item) => {
    console.log(item);
    return (
      <div className="result-wrapper">
        <span className="result-span">{item.name}</span>
      </div>
    );
  };

  return (
    <div style={{ width: 300 }} className="mx-10 my-auto">
      <ReactSearchAutocomplete
        items={items}
        onSelect={handleOnSelect}
        onFocus={handleOnFocus}
        onClear={handleOnClear}
        formatResult={formatResult}
        placeholder="Search"
        styling={{
          height: "40px",
          border: "1px solid gray",
          borderRadius: "0",
          outline: "none",
          hoverBackgroundColor: "#202020",
          color: "#fff",
          backgroundColor: "black",
          boxShadow: "none",
          zIndex: "3",
          iconColor: "white",
          placeholderColor: "white",
        }}
      />
    </div>
  );
}
