using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Tanks
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main(string[] args)
        {
            MenuForm menu;

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            //Controller_MainForm cmf;
            //switch(args.Length)
            //{
            //    case 0: cmf = new Controller_MainForm(); break;
            //    case 1: cmf = new Controller_MainForm(Int32.Parse(args[0])); break;
            //    case 2: cmf = new Controller_MainForm(Int32.Parse(args[0]), Int32.Parse(args[1])); break;
            //    case 3: cmf = new Controller_MainForm(Int32.Parse(args[0]), Int32.Parse(args[1]), Int32.Parse(args[2])); break;
            //    case 4: cmf = new Controller_MainForm(Int32.Parse(args[0]), Int32.Parse(args[1]), Int32.Parse(args[2]), Int32.Parse(args[3])); break;
            //    default: cmf = new Controller_MainForm(); break;
            //}

            menu = new MenuForm();
            Application.Run(menu);
        }
    }
}
