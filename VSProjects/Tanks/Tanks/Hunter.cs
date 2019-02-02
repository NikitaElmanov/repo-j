using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tanks
{
    public class Hunter : Tank
    {
        HunterImg hunterImg = new HunterImg();

        int target_x, target_y;

        private bool flagTurn = false;

        public Hunter(int sizeField, int x, int y) : base(sizeField, x, y) 
        {
            this.PutImgMass();
            this.PutImg();
        }

        public bool FlagTurn
        {
            get { return flagTurn; }
            set { flagTurn = value; }
        }

        private void PutImgMass()
        {
            switch (Direct_x)
            {
                case 1:
                    imgMass = hunterImg.Right;
                    break;
                case -1:
                    imgMass = hunterImg.Left;
                    break;
            }

            switch (Direct_y)
            {
                case 1:
                    imgMass = hunterImg.Down;
                    break;
                case -1:
                    imgMass = hunterImg.Up;
                    break;
            }
        }

        new public void Turn(int x, int y) //PackMan's coordianters  (x, y)
        {
                if (x > this.x)
                {
                    //right
                    this.Direct_x = 1;
                    this.Direct_y = 0;
                }
                else if (x < this.x)
                {
                    //left
                    this.Direct_x = -1;
                    this.Direct_y = 0;
                }
                else if (y > this.y)
                {
                    //up
                    this.Direct_x = 0;
                    this.Direct_y = 1;
                }
                else
                {
                    //down
                    this.Direct_x = 0;
                    this.Direct_y = -1;
                }
        }

        new public void Run(int PackManX, int PackManY)
        {
            this.target_x = PackManX;
            this.target_y = PackManY;

            this.Transparent();

            x += direct_x;
            y += direct_y;

            //tank is on the crossroad
            if (Math.IEEERemainder(x, 64) == 0 && Math.IEEERemainder(y, 64) == 0 &&
                                                                          x >= 0 && // if tank is inside of our VIEW edges [0, 416], then tank can't turn 
                                                                          x <= 416 &&
                                                                          y >= 0 &&
                                                                          y <= 416)
            {
                this.Turn(target_x, target_y);
                this.PutImgMass();

                flagTurn = false;
            }

            PutImg();
        }

        new public void TurnAround()
        {
            Direct_x = -1 * Direct_x;
            Direct_y = -1 * Direct_y;

            PutImgMass();
        }
    }
}
