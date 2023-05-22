package com.example.lab_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.lab_2.databinding.FragmentMainBinding

class MainFragment : Fragment(), OnLanguageClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val programmingLanguages = listOf(
            ProgrammingLanguage(
                name = "Java",
                shortDescription = "Java is a widely-used programming language.",
                longDescription = "Java is a general-purpose programming language that is designed to have as few implementation dependencies as possible. It is widely used for developing desktop, web, and mobile applications.",
                url = "https://upload.wikimedia.org/wikipedia/uk/8/85/%D0%9B%D0%BE%D0%B3%D0%BE%D1%82%D0%B8%D0%BF_Java.png"
            ),
            ProgrammingLanguage(
                name = "Python",
                shortDescription = "Python is a popular programming language for various applications.",
                longDescription = "Python is an interpreted, high-level programming language known for its simplicity and readability. It is widely used in web development, scientific computing, artificial intelligence, and data analysis.",
                url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRX9KYoFpX9v-HF45IjK17OC4jhT19I55y0Fw&usqp=CAU"
            ),
            ProgrammingLanguage(
                name = "JavaScript",
                shortDescription = "JavaScript is a scripting language for web development.",
                longDescription = "JavaScript is a versatile scripting language primarily used for adding interactivity to web pages. It enables dynamic content, form validation, and enhances the user experience on the client-side of web applications.",
                url = "https://desarrolloweb.com/storage/serie_images/SiXTEAgkHYnvpf5x9grsKz4YQDcbUBE2EuLLSaR8.jpeg"
            ),
            ProgrammingLanguage(
                name = "Kotlin",
                shortDescription = "Kotlin is a modern programming language for Android app development.",
                longDescription = "Kotlin is a statically-typed programming language developed by JetBrains. It is fully interoperable with Java and has gained popularity for Android app development due to its concise syntax, enhanced safety features, and excellent tooling support.",
                url = "data:image/png;base64,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"
            ),
            ProgrammingLanguage(
                name = "C#",
                shortDescription = "C# is a versatile programming language developed by Microsoft.",
                longDescription = "C# is a multi-purpose programming language developed by Microsoft. It is widely used for developing Windows applications, web services, games, and other software. C# is part of the .NET framework.",
                url = "https://static.gunnarpeipman.com/wp-content/uploads/2009/10/csharp-featured.png"
            ),
            ProgrammingLanguage(
                name = "Swift",
                shortDescription = "Swift is a programming language for iOS, macOS, watchOS, and tvOS app development.",
                longDescription = "Swift is a powerful and intuitive programming language developed by Apple. It is used for developing applications across Apple platforms, including iOS, macOS, watchOS, and tvOS. Swift is designed to be fast, safe, and expressive.",
                url = "https://developer.apple.com/swift/images/swift-og.png"
            )
        )


        val adapter = LanguageAdapter(programmingLanguages, this)

        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = adapter
    }

    override fun onClick(language: ProgrammingLanguage) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToDetailsFragment(language)
        )
    }
}

interface OnLanguageClickListener {
    fun onClick(language: ProgrammingLanguage)
}