import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import VerifyPhoneNumber from "./verifyPhoneNumber.js/pages/index.js";
import ForgotPassword from "./verifyPhoneNumber.js/pages/indexs.js";

function App() {
	return (
		<div>
			<Router>
				<Routes>
					<Route path="/getStarted" element={<VerifyPhoneNumber />} />
					<Route path="/" element={<ForgotPassword />} />
				</Routes>
			</Router>
		</div>
	);
}

export default App;
