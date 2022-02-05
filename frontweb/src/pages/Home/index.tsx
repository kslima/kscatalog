import NavBar from 'components/NavBar';
import {ReactComponent as MainImage} from 'assets/images/main-image.svg';
import './styles.css';
import ButtonIcon from 'components/ButtonIcon';

const Home = () => {
  return (
    <>
      <NavBar />
      <div className='home-container'>
        <div className='home-card'>
            <div className='home-content-container'>
                <h1>Conheça o melhor catálogo de produtos</h1>
                <p>Ajudaremos você a encontrar os melhores produtos disponíveis no mercado.</p>
                <ButtonIcon />
            </div>
            <div className='home-image-container'>
                <MainImage />
            </div>
        </div>
      </div>
    </>
  );
};

export default Home;
