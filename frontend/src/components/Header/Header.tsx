import "./Header.css"
import {Link} from "react-router-dom";
import {UserProfile} from "../../assets/UserProfileEntities.ts";

type headerProps = {
    userProfile: UserProfile | undefined
    login: () => void
    logout: () => void
}
export default function Header(props: headerProps) {
    return (
        <header>
            <div>
                <Link to={"/"}>
                    <img
                        src={"https://banner2.cleanpng.com/20190730/shy/kisspng-photographic-film-movie-camera-cinema-website-and-mobile-application-development-service-5d3fc924ce3b33.8538265315644613488447.jpg"}
                        alt={"framefiesta logo"}/>
                    <h1>FrameFiesta</h1>
                </Link>
            </div>
            <nav>
                <ul>
                    <li><Link to={"/movies"}>Alle Filme</Link></li>
                    <li><Link to={"/favorites"}>Favoriten</Link></li>

                    {
                        props.userProfile?.name
                            ?
                            <div className="userProfile-container">
                                <button>{props.userProfile?.name}<img src={props.userProfile.avatarUrl} alt="avatar"/>
                                </button>
                                <button className="logout-btn" onClick={props.logout}>Logout</button>
                            </div>
                            :
                            <div className="userProfile-container">
                                <button className="login-btn" onClick={props.login}>Login</button>
                            </div>


                    }


                </ul>
            </nav>
        </header>
    )
        ;
}

