using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;

namespace Tanks
{
    class Model
    {
        private event EventHandler IncScore;

        private event DChangeGameStatus ChangeStatus;

        int sizeField;
        int ammountTanks; 
        int ammoutApples; 
        private int speedGame;

        public GameStatus gameStatus;

        List<Tank> tanks;

        List<Apple> apples;

        List<Bullet> bullets;

        List<FireTank> fireTanks;

        List<FireTank> singleBoom;

        PackMan packMan;

        Random r;

        public List<FireTank> SingleBoom
        {
            get { return singleBoom; }
        } 

        public List<FireTank> FireTanks
        {
            get { return fireTanks; }
        }

        public List<Bullet> Bullets
        {
            get { return bullets; }
        }

        public PackMan PackMan
        {
            get { return packMan; }
        }

        public List<Apple> Apples
        {
            get { return apples; }
        }

        public int SpeedGame 
        {
            get
            {
                return speedGame;
            }
        }

        public List<Tank> Tanks
        {
            get { return tanks; }
        }
        public Wall wall;

        public Model(int sizeField, int ammountTanks, int ammoutApples, int speedGame, EventHandler incScore, DChangeGameStatus ChangeStatus)
        {
            this.sizeField = sizeField;
            this.ammountTanks = ammountTanks;
            this.ammoutApples = ammoutApples;
            this.speedGame = speedGame;

            this.IncScore = incScore;

            this.ChangeStatus = ChangeStatus;

            r = new Random();

            NewGame();
        }

        public void Play()
        {
            while (gameStatus == GameStatus.playing)
            {
                Thread.Sleep(speedGame);

                packMan.Run();

                for (var i = 0; i < bullets.Count; i++ )
                {
                    bullets[i].Run();
                }

                for (var i = 0; i < tanks.Count; i++ )
                {
                    if (tanks[i] is Hunter)
                    {
                        ((Hunter)tanks[0]).Run(packMan.X, packMan.Y);
                    }
                    else
                    {
                        tanks[i].Run();
                    }
                }

                foreach(FireTank ft in fireTanks){
                    ft.Fire();
                }

                CollideBulletTank();

                BulletsCollideEdges();

                TanksCollide();

                TakeApple();

                TanksCollidePackMan();

                YouWin();
            }
        }

        private void BulletsCollideEdges()
        {
            for (var j = 0; j < bullets.Count; j++)
            {
                if (bullets[j].isOutOfEdges())
                {
                    singleBoom.Add(new FireTank(bullets[j].X - 32/2, bullets[j].Y - 32/2));
                    bullets.RemoveAt(j);
                }
            }
        }

        private void CollideBulletTank()
        {
            for (var j = 0; j < bullets.Count; j++)
            {
                for (var i = 0; i < tanks.Count; i++)
                {
                    if (tanks[i].X      < bullets[j].X  &&
                        tanks[i].X + 30 > bullets[j].X  &&
                        tanks[i].Y      < bullets[j].Y  &&
                        tanks[i].Y + 30 > bullets[j].Y)
                    {
                        fireTanks.Add(new FireTank(tanks[i].X, tanks[i].Y));
                        tanks.RemoveAt(i);
                    }
                }
            }
        }

        void TanksCollidePackMan()
        {
            for (var i = 0; i < tanks.Count; i++ )
            {
                if (tanks[i].X < packMan.X + 16 &&
                    tanks[i].X + 16 > packMan.X &&
                    tanks[i].Y < packMan.Y + 16 &&
                    16 + tanks[i].Y > packMan.Y)
                {
                    gameStatus = GameStatus.loser;
                    ChangeStatus();
                }
            }
        }

        void createTanks()
        {
            tanks.Add(new Hunter(sizeField, r.Next(6) * 64, r.Next(5) * 64));//1 element of mass tanks will be HUNTER

            int x = 0, y = 0;

            while(tanks.Count < ammountTanks)
            {
                x = r.Next(6) * 64;
                y = r.Next(4) * 64;

                foreach(var t in tanks){
                    while (t.X == x && t.Y == y)
                    {
                        x = r.Next(6) * 64;
                        y = r.Next(4) * 64;
                    }
                }

                tanks.Add(new Tank(sizeField, x, y));
            }
        }

        void createApples()
        {
            int x = 0, y = 0;

            while (apples.Count < ammoutApples)
            {
                x = r.Next(6) * 64;
                y = r.Next(6) * 64;

                foreach (var t in apples)
                {
                    while (t.X == x && t.Y == y)
                    {
                        x = r.Next(6) * 64;
                        y = r.Next(6) * 64;
                    }
                }

                apples.Add(new Apple(x, y));
            }
        }

        void TakeApple()
        {
            for (var i = 0; i < apples.Count; i++)
            {
                if (apples[i].X == packMan.X && apples[i].Y == packMan.Y)
                {
                    apples.RemoveAt(i);
                    
                    if(IncScore != null) 
                    {
                        IncScore.Invoke(new Object(), EventArgs.Empty);
                    }
                }
            }
        }

        private void TanksCollide()
        {
            for (var i = 0; i < tanks.Count; i++)
            {
                for (var j = i + 1; j < tanks.Count; j++)
                {
                    if (tanks[i].X < tanks[j].X + 32 &&
                        tanks[i].X + 32 > tanks[j].X &&
                        tanks[i].Y < tanks[j].Y + 32 &&
                        32 + tanks[i].Y > tanks[j].Y)
                    {
                        //after collision hunter (tanks[0]) has a simple texture of tank
                        if(tanks[0] is Hunter & i == 0) {

                            ( (Hunter)tanks[i] ).TurnAround();
                        }
                        else
                        {
                            tanks[i].TurnAround();
                        }

                        tanks[j].TurnAround();
                    }
                }
            }
        }

        public void NewGame()
        {
            bullets = new List<Bullet>();

            tanks = new List<Tank>();
            createTanks();//check position of all tanks only

            apples = new List<Apple>();
            createApples(); // check position tanks and apples together

            wall = new Wall();

            fireTanks = new List<FireTank>();

            packMan = new PackMan(sizeField);

            singleBoom = new List<FireTank>();

            gameStatus = GameStatus.stoping;
        }

        public void YouWin()
        {
            if(apples.Count <= 0 && tanks.Count <= 0) //ammoutApples == apples.Count && ammoutTanks == tanks.Count
            {
                gameStatus = GameStatus.winner;
                ChangeStatus();
            }
        }
    }
}
