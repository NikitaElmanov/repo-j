using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using Tanks.Interfaces;

namespace Tanks
{
    class Wall : IPicture
    {
        WallImg wallImg = new WallImg();
        Image img;

        public Image Img
        {
            get { return img; }
        }

        public Wall()
        {
            img = wallImg.Img;
        }
    }
}
