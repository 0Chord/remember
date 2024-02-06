import React, { useState } from "react";
import styled from "styled-components";
import "../css/auth.css";
import backImg from "../img/backImg.webp";
import { forgetPasswordRequest } from "../server/server";
import { Link, useNavigate } from "react-router-dom";

const Wrapper = styled.div`
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100vh;
    background-image: url(${backImg});
    background-size: cover;
`;

const FormContainer = styled.div`
    width: 40%;
    height: 40%;
    padding: 32px;
    border-radius: px;
    background-color: #313338;
    color: white;
`;

const Content = styled.form`
    height: 55%;
    margin-top: 60px;
    margin-right: 30px;
    margin-left: 30px;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
`;

export default function ForgetPassword() {
    const navigate = useNavigate();

    const [email, setEmail] = useState("");

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handleForgotPassword = (e) => {
        e.preventDefault();

        // 여기서 logInRequest 함수를 호출
        forgetPasswordRequest(email)
            .then((status) => {
                if (status === 200)
                    navigate("/login");
            });
    };

    return (
        <Wrapper>
            <FormContainer>
                <h1 style={{ margin: "0px" }}>당신의 비번을 찾아드립니다</h1>
                <Content onSubmit={handleForgotPassword}>
                    <label name="userId">
                        이메일
                        <input
                            type="email"
                            value={email}
                            onChange={handleEmailChange}
                            name="userId"
                            required
                        ></input>
                    </label>
                    <button id="submit" type="submit">
                        비밀번호 찾기
                    </button>
                    <br />
                    <Link to="/" style={{ color: "white" }}>뒤로</Link>
                </Content>
            </FormContainer>
        </Wrapper>
    );
};