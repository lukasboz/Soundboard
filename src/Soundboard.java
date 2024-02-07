
//Author: Lukas
//imports
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.Color;
import java.awt.Toolkit;

//start class
@SuppressWarnings({})
public class Soundboard implements ActionListener {

	// fields
	private JFrame soundboardFrame = new JFrame();
	private JPanel soundboardPanel = new JPanel();
	private JButton helpButton = new JButton("Help");
	private JButton convertButton = new JButton("File Conversion");

	private int screenX = Toolkit.getDefaultToolkit().getScreenSize().width;
	private int screenY = Toolkit.getDefaultToolkit().getScreenSize().height;

	private JButton sound1 = new JButton("you stupid ni");
	private JButton sound2 = new JButton("fart");

	/*
	 * Program University Avg for Admission Prerequisites Tuition Cost (Budget)
	 * Distance from Markham (km) Co-op Program Length Link
	 */

	public Soundboard() {

		frontPanel();
		frameSetup();
		soundButtonSetup();

	}

	// run everything that is needed on the user ratings panel
	private void frontPanel() {

		// first add the user ratings
		soundboardFrame.setSize(screenX, screenY);
		soundboardFrame.setLayout(null);

		// add the help button
		helpButton.setBounds(20, 920, 100, 100);
		helpButton.setBackground(Color.WHITE);
		helpButton.setForeground(Color.BLACK);
		soundboardFrame.add(helpButton);
		helpButton.addActionListener(this);

		// add the conversion button
		convertButton.setBounds(20, 700, 200, 100);
		convertButton.setBackground(Color.WHITE);
		convertButton.setForeground(Color.BLACK);
		soundboardFrame.add(convertButton);
		convertButton.addActionListener(this);

	}

	// set up our frame
	private void frameSetup() {

		// set the size of the frame
		soundboardFrame.setSize(1920, 1080);
		soundboardFrame.setLayout(null);
		soundboardFrame.setTitle("Lukas' Soundboard");

		soundboardFrame.add(soundboardPanel);

		// if the close button is pressed the program is terminated
		soundboardFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		;
		soundboardFrame.setVisible(true);
	}

	// set up sound buttons
	private void soundButtonSetup() {

		sound1.setBounds(20, 20, 150, 50);
		sound1.setBackground(Color.WHITE);
		sound1.setForeground(Color.BLACK);
		soundboardFrame.add(sound1);
		sound1.addActionListener(this);

		sound2.setBounds(20, 90, 150, 50);
		sound2.setBackground(Color.WHITE);
		sound2.setForeground(Color.BLACK);
		soundboardFrame.add(sound2);
		sound2.addActionListener(this);

	}

	@Override
	// this method runs the other methods below this one when a certain button is
	// pressed
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == helpButton) {
			JOptionPane.showMessageDialog(null, "mf it's a soundboard click the buttons", "Help",
					JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getSource() == sound1) {

			try {
				// create a new input stream and grab the file from the sounds folder
				AudioInputStream audio = AudioSystem
						.getAudioInputStream(new File("sounds/youstupidni.wav").getAbsoluteFile());
				Clip sound1 = AudioSystem.getClip(); // create a clip called startGame and get the clip from the
														// "audio

				sound1.open(audio);
				sound1.start(); // play the clip/sound
			} catch (Exception ex) { // print in console if the clip doesn't work for whatever reason
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}

		}

		if (e.getSource() == sound2) {

			try {
				// create a new input stream and grab the file from the sounds folder
				AudioInputStream audio = AudioSystem.getAudioInputStream(new File("sounds/fart.wav").getAbsoluteFile());
				Clip sound = AudioSystem.getClip(); // create a clip called startGame and get the clip from the
													// "audio

				sound.open(audio);
				sound.start(); // play the clip/sound
			} catch (Exception ex) { // print in console if the clip doesn't work for whatever reason
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}

		}

		if (e.getSource() == convertButton) {
			String filepath = "";
			JFileChooser pick = new JFileChooser();
			int returnVal = pick.showOpenDialog(null);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				filepath = pick.getSelectedFile().getPath();
			}

			InputStream targetStream = null;
			try {
				targetStream = new FileInputStream(new File(filepath));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

			System.out.println(new Converter(targetStream).toString());

		}

	}

	public static void mp3ToWav(File mp3Data) throws UnsupportedAudioFileException, IOException {
		// open stream
		AudioInputStream mp3Stream = AudioSystem.getAudioInputStream(mp3Data);
		AudioFormat sourceFormat = mp3Stream.getFormat();
		// create audio format object for the desired stream/audio format
		// this is *not* the same as the file format (wav)
		AudioFormat convertFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, sourceFormat.getSampleRate(), 16,
				sourceFormat.getChannels(), sourceFormat.getChannels() * 2, sourceFormat.getSampleRate(), false);
		// create stream that delivers the desired format
		AudioInputStream converted = AudioSystem.getAudioInputStream(convertFormat, mp3Stream);
		// write stream into a file with file format wav
		AudioSystem.write(converted, Type.WAVE, new File("C:\\temp\\out.wav"));
	}

}// end class