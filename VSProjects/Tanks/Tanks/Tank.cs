using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using Tanks.Interfaces;

namespace Tanks
{
    public class Tank : IRun, ITurn, ITransparent
    {
        protected int x, y, direct_x, direct_y;

        protected int sizeField;

        protected static Random r;

        private TankImg tankImg = new TankImg();

        protected Image[] imgMass;
        protected Image currentImg;

        public Image CurrentImg
        {
            get { return currentImg; }
        }

        public Tank(int sizeField, int x, int y)
        {
            r = new Random();

            this.x = x;
            this.y = y;
            this.sizeField = sizeField;

            PutStartDirection();

            PutImgMass();

            PutImg();
        }

        protected void PutStartDirection()
        {
            direct_x = r.Next(-1, 2);

            if (direct_x == 0)
            {
                while (direct_y == 0)
                    direct_y = r.Next(-1, 2);
            }
            else
            {
                direct_y = 0;
            }
        }

        public int Direct_y
        {
            get { return direct_y; }
            set 
            { 
                if(value == 0 || value == 1 || value == -1) 
                {
                    direct_y = value;
                }
                else
                {
                    direct_y = 0;
                }
            }
        }

        public int Direct_x
        {
            get { return direct_x; }
            set
            {
                if (value == 0 || value == 1 || value == -1)
                {
                    direct_x = value;
                }
                else
                {
                    direct_x = 0;
                }
            }
        }

        public int Y
        {
            get { return y; }
            set { y = value; }
        }

        public int X
        {
            get { return x; }
            set { x = value; }
        }
        
        public void Run()
        {
            this.Transparent();

            x += direct_x;
            y += direct_y;

            //tank is on the crossroad
            if (Math.IEEERemainder(x, 64) == 0 && Math.IEEERemainder(y, 64) == 0   &&
                                                                          x >= 0   && // if tank is inside of our VIEW edges [0, 416], then tank can't turn 
                                                                          x <= 416 &&
                                                                          y >= 0   &&
                                                                          y <= 416 ) 
            {
                this.Turn();
                this.PutImgMass();
            }

            PutImg();
        }

        public void Turn()
        {
            //tank will drive on vertical
            if (r.Next(100) < 50)
            {
                if (Direct_x == 0)
                {
                    Direct_y = 0;

                    while (Direct_x == 0)
                    {
                        Direct_x = r.Next(-1, 2);
                    }
                }
            }
            else  //tank will drive on horizontal
            {
                if (Direct_y == 0)
                {
                    Direct_x = 0;

                    while (Direct_y == 0)
                    {
                        Direct_y = r.Next(-1, 2);
                    }
                }
            }
        }

        public void TurnAround()
        {
            Direct_x = -1 * Direct_x;
            Direct_y = -1 * Direct_y;

            PutImgMass();
        }

        public void Transparent()
        {
            if (X > sizeField + 32)
            {
                X = -32;
            }

            if (X < -32)
            {
                X = sizeField + 32;
            }

            if (Y > sizeField + 32)
            {
                Y = -32;
            }

            if (Y < -32)
            {
                Y = sizeField + 32;
            }
        }

        protected int k;
        protected void PutImg()
        {
            currentImg = imgMass[k];

            k++;

            if (k == 5)
            {
                k = 0;
            }
        }

        private void PutImgMass()
        {
            switch(Direct_x) 
            {
                case 1:
                    imgMass = tankImg.Right;
                    break;
                case -1:
                    imgMass = tankImg.Left;
                    break;
            }

            switch (Direct_y)
            {
                case 1:
                    imgMass = tankImg.Down;
                    break;
                case -1:
                    imgMass = tankImg.Up;
                    break;
            }
        }
    }
}
