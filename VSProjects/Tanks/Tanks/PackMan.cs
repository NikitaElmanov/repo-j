using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Tanks.Interfaces;

namespace Tanks
{
    public class PackMan : IRun, ITurn, ITransparent
    {
        PackManImg packManImg = new PackManImg();
        Image[] packManMass;
        Image currentImg;

        int x, y, direct_x, direct_y, nextDirectX, nextDirectY;
       
        int sizeField;

        public PackMan(int sizeField)
        {
            this.sizeField = sizeField;

            this.x = 416 / 2 - 32/2;
            this.y = 416 - 32;

            NextDirectX = 0;
            NextDirectY = -1;

            Direct_x = NextDirectX;
            Direct_y = NextDirectY;

            PutImgMass();

            PutImg();
        }

        public int NextDirectY
        {
            get { return nextDirectY; }
            set
            {
                if (value == 0 || value == 1 || value == -1)
                {
                    nextDirectY = value;
                }
                else
                {
                    nextDirectY = 0;
                }
            }
        }

        public int NextDirectX
        {
            get { return nextDirectX; }
            set
            {
                if (value == 0 || value == 1 || value == -1)
                {
                    nextDirectX = value;
                }
                else
                {
                    nextDirectX = 0;
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

        public int Direct_y
        {
            get { return direct_y; }
            set
            {
                if (value == 0 || value == 1 || value == -1)
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

        public Image CurrentImg
        {
            get { return currentImg; }
        }

        public void Run()
        {
            this.Transparent();

            x += direct_x;
            y += direct_y;

            //tank is on the crossroad
            if (Math.IEEERemainder(x, 64) == 0 && Math.IEEERemainder(y, 64) == 0)
            {
                this.Turn();
                this.PutImgMass();
            }
            
            PutImg();
        }

        void PutImgMass()
        {
            switch (Direct_x)
            {
                case 1:
                    packManMass = packManImg.Right;
                    break;
                case -1:
                    packManMass = packManImg.Left;
                    break;
            }

            switch (Direct_y)
            {
                case 1:
                    packManMass = packManImg.Down;
                    break;
                case -1:
                    packManMass = packManImg.Up;
                    break;
            }
        }

        int k;
        void PutImg()
        {
            currentImg = packManMass[k];

            k++;

            if (k == 5)
            {
                k = 0;
            }
        }

        public void Turn()
        {
            Direct_x = nextDirectX;
            Direct_y = nextDirectY;
        }

        public void TurnAround()
        {
            throw new NotImplementedException();
        }

        public void Transparent()
        {
            if (X > sizeField + 30)
            {
                X = -30;
            }

            if (X < -30)
            {
                X = sizeField + 30;
            }

            if (Y > sizeField + 30)
            {
                Y = -30;
            }

            if (Y < -30)
            {
                Y = sizeField + 30;
            }
        }
    }
}
