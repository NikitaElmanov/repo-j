using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace Tanks
{
    public class FireTanksImg
    {
        Image[] img = new Image[] 
        { 
           Properties.Resources.fire_1,
           Properties.Resources.fire_2,
           Properties.Resources.fire_3,
           Properties.Resources.fire_4,
        };

        public Image[] Img
        {
            get { return img; }
            set { img = value; }
        }
    }
}
