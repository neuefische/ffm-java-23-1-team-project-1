import "./Header.css"
import {Link} from "react-router-dom";

export default function Header() {
    return (
        <header>
            <Link to={"/movies"}><h1>FrameFiesta</h1></Link>
        </header>
    );
}

