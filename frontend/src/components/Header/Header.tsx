import "./Header.css"
import {Link} from "react-router-dom";

export default function Header() {
    return (
        <header>
            <div>
                <Link to={"/"}>
                    <img src={"https://banner2.cleanpng.com/20190730/shy/kisspng-photographic-film-movie-camera-cinema-website-and-mobile-application-development-service-5d3fc924ce3b33.8538265315644613488447.jpg"} alt={"framefiesta logo"}/>
                    <h1>FrameFiesta</h1>
                </Link>
            </div>
            <nav>
                <ul>
                    <li><Link to={"/movies"}>Alle Filme</Link></li>
                    <li><Link to={"/movies"}>Favoriten</Link></li>
                </ul>
            </nav>
        </header>
    );
}

