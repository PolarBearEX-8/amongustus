#version 120

uniform float offset;
uniform sampler2D tex;
uniform vec2 resolution;

void main() {
    vec2 uv = gl_FragCoord.xy / resolution;
    vec4 color = texture2D(tex, uv) * 4.0;
    color += texture2D(tex, (gl_FragCoord.xy - 0.5 * offset) / resolution);
    color += texture2D(tex, (gl_FragCoord.xy + 0.5 * offset) / resolution);
    color += texture2D(tex, (gl_FragCoord.xy + vec2(0.5, -0.5) * offset) / resolution);
    color += texture2D(tex, (gl_FragCoord.xy - vec2(0.5, -0.5) * offset) / resolution);

    color /= 8.0;
    color.a = 1.0;
    gl_FragColor = color;
}