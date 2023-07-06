import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { toast } from "react-hot-toast;";
import MapIcon from "../assets/verifyPhoneNumber/map-icon.svg";

const VerifyPhoneNumber = () => {
	const navigate = useNavigate();
	const [phoneNumber, setPhoneNumber] = useState("");
	const [error, setError] = useState("");

	const checkPhoneNumber = (phoneNumber) => {
		const phoneNumberRegex =
			/((^090)([23589]))|((^070)([1-9]))|((^080)([2-9]))|((^081)([0-9]))(\d{7})/;
		return phoneNumberRegex.test(phoneNumber);
	};
	const handleSubmit = async (e) => {
		setError("");
		e.preventDefault();
		//`/^(\\+?234|0)[789]\\d{9}$/`

		console.log(checkPhoneNumber(phoneNumber));

		if (!checkPhoneNumber(phoneNumber)) {
			setError("Invalid phone number");
		}
		try {
			const response = await VerifyPhoneNumber(phoneNumber);
			if (response.success) {
				toast.success(response.message);
				setPhoneNumber("");
			}
		} catch (err) {
			toast.error(err.message);
		}
	};
	return (
		<div className="h-screen bg-orange-100 flex flex-col justify-center items-center">
			<div className="w-full h-10 flex flex-row justify-start px-2 top-2/4">
				<img className="pt-2 h-10" src={MapIcon} alt="logo" />
				<h2 className="text-4xl font-semibold text-primary">Spedire</h2>
			</div>

			<div className="bg-white shadow-md rounded-md  w-96 p-5">
				<form onSubmit={handleSubmit}>
					<h1>Sign up with Spedire</h1>
					<br></br>
					<h3>Verify Phone Number</h3>
					<hr className="mt-3" />
					<input
						className="mt-3 bg-zinc-200 text-white w-full"
						type="text"
						name="phoneNumber"
						placeholder="Enter phone number"
						onChange={(e) => {
							setPhoneNumber(e.target.value);
						}}
						value={phoneNumber}
						required
					/>

					{error ? <p>{error}</p> : ""}
					<br />
					<button className="contained-btn mt-3 w-full" type="submit">
						Verify Phone Number
					</button>
				</form>
				<h6 onClick={navigate("/login")}>Sign in to an existing account</h6>
			</div>
		</div>
	);
};

export default VerifyPhoneNumber;
