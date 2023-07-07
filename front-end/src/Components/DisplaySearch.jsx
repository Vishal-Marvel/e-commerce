import { useState } from "react";
import { SearchIcon } from "@heroicons/react/outline";
const data = [
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

export default function Search() {
  const [value, setValue] = useState("");

  const onChange = (event) => {
    setValue(event.target.value);
  };

  const onSearch = (searchTerm) => {
    setValue(searchTerm);
    // our api to fetch the search result
    console.log("search ", searchTerm);
  };

  return (
    <div className="down-search">
      {/* <h1>Search</h1> */}

      <div className="search-container">
        <div className="search-inner">
          <div
            className="relative w-full bg-white shadow-xl"
            id="search-content"
          >
            <div className="mx-auto flex absolute z-10 w-full text-black">
              <input
                id="searchfield"
                type="search"
                placeholder="Search..."
                autoFocus="autofocus"
                className="w-full text-grey-800 transition focus:outline-none focus:ring-0 focus:border-transparent p-2 appearance-none border-r-0 text-xl lg:text-2xl"
                onChange={onChange}
                value={value}
              />
              <button
                className="bg-white p-5 border border-solid border-black border-l-0"
                onClick={() => onSearch(value)}
              >
                <SearchIcon className="w-6 h-6" aria-hidden="true" />
              </button>
            </div>
          </div>
        </div>
        <div className="dropdown">
          {data
            .filter((item) => {
              const searchTerm = value.toLowerCase();
              const fullName = item.name.toLowerCase();

              return (
                searchTerm &&
                fullName.startsWith(searchTerm) &&
                fullName !== searchTerm
              );
            })
            .slice(0, 10)
            .map((item) => (
              <div
                onClick={() => onSearch(item.name)}
                className="dropdown-row"
                key={item.name}
              >
                {item.name}
              </div>
            ))}
        </div>
      </div>
    </div>
  );
}
