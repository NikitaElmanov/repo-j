using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tanks
{
    public class Bullet : IRun
    {
        BulletImg bulletImg = new BulletImg();
        Image currentImg;

        int x, y, speed = 5;

        public int width = 7;
        public int height = 15;

        int packManDirectX, packManDirectY;

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

        public Image CurrentImg
        {
            get { return currentImg; }
            set { currentImg = value; }
        }

        public Bullet(int packManX, int packManY, int packManDirectX, int packManDirectY)
        {
            this.x = packManX + 26/2;
            this.y = packManY + 26/2;
            this.packManDirectX = packManDirectX;
            this.packManDirectY = packManDirectY;

            PutImg();
        }

        private void PutImg()
        {
            if(packManDirectX == 1) 
            {
                currentImg = bulletImg.Img_right;
            }
            else if (packManDirectX == -1) 
            {
                currentImg = bulletImg.Img_left;
            }
            else if (packManDirectY == 1)
            {
                currentImg = bulletImg.Img_down;
            }
            else if (packManDirectY == -1)
            {
                currentImg = bulletImg.Img_up;
            }
            else
            {
                throw new Exception("Direction was not selected");
            }
        }

        public void Run()
        {
            x += packManDirectX * speed;
            y += packManDirectY * speed;
        }

        internal bool isOutOfEdges()
        {
            if(this.x < 0 || this.x > 416 || this.y < 0 || this.y > 416) 
            {
                return true;
            }

            return false;
        }
    }
}
