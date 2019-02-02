using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace Tanks
{
    public class FireTank
    {
        FireTanksImg fireImg = new FireTanksImg();

        Image currentImg;

        Image[] imgMass;

        public Image CurrentImg
        {
            get { return currentImg; }
        }

        int x, y;

        public int Y
        {
            get { return y; }
        }

        public int X
        {
            get { return x; }
        }

        public FireTank(int x, int y)
        {
            this.x = x;
            this.y = y;

            PutImgMass();
            PutImg();
        }

        public void Fire()
        {
            PutImg();
        }

        protected int k;
        protected void PutImg()
        {
            currentImg = imgMass[k];

            k++;

            if (k == 4)
            {
                k = 0;
            }
        }

        private void PutImgMass()
        {
            imgMass = fireImg.Img;
        }
    }
}
