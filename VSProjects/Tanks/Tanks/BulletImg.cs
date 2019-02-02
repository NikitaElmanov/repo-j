using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Tanks
{
    class BulletImg
    {
        Image img_up = Properties.Resources.bullet_up;
        Image img_right = Properties.Resources.bullet_right;
        Image img_left = Properties.Resources.bullet_left;
        Image img_down = Properties.Resources.bullet_down;

        public Image Img_down
        {
            get { return img_down; }
            set { img_down = value; }
        }

        public Image Img_up
        {
            get { return img_up; }
            set { img_up = value; }
        }

        public Image Img_right
        {
            get { return img_right; }
            set { img_right = value; }
        }

        public Image Img_left
        {
            get { return img_left; }
            set { img_left = value; }
        }
    }
}
