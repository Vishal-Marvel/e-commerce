import React from "react";
import { GoogleLogin } from 'react-google-login';
import { gapi } from 'gapi-script';
import axios from "../axiosConfig";

const responseGoogle = async (response) => {

    const email = response.profileObj.email;
    const username = response.profileObj.name;
    const password = response.profileObj.googleId;
    try {
        const auth_response = await axios.post("/auth/oauth2/callback/google", {
            username: username,
            email: email,
            password: password
        });
        if(auth_response.data.token){
            sessionStorage.setItem("token", auth_response.data.token);
            sessionStorage.setItem("role", auth_response.data.role);
        }

    } catch (error) {
        console.log(error);
        alert(error);
    }
};
    gapi.load("client:auth2", () => {
        gapi.client.init({
            clientId:
                "811320824769-vgh9cfj27gqcc84p7somqt2c73vbst95.apps.googleusercontent.com",
            plugin_name: "chat",
        });
    });

    const GoogleLoginButton = () => {
        return (
            <GoogleLogin
                clientId="811320824769-vgh9cfj27gqcc84p7somqt2c73vbst95.apps.googleusercontent.com"
                buttonText="Login"
                onSuccess={responseGoogle}
                onFailure={responseGoogle}
                cookiePolicy={'single_host_origin'}
            />
        );
    };

    export default GoogleLoginButton;

